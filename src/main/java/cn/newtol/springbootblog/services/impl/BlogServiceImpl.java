package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.dao.ContentInfo;
import cn.newtol.springbootblog.dao.ContentPraise;
import cn.newtol.springbootblog.dao.ContentType;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.exceptions.MyException;
import cn.newtol.springbootblog.myEnum.ResultEnum;
import cn.newtol.springbootblog.repository.ContentInfoRepository;
import cn.newtol.springbootblog.repository.ContentPraiseRepository;
import cn.newtol.springbootblog.repository.ContentTypeRepository;
import cn.newtol.springbootblog.services.BlogService;
import cn.newtol.springbootblog.utils.RedisUtil;
import cn.newtol.springbootblog.utils.ResultUtil;
import cn.newtol.springbootblog.utils.ResumeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:58 2019/5/17
 * @params:
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private ContentTypeRepository contentTypeRepository;

    @Resource
    private ContentInfoRepository contentInfoRepository;

    @Resource
    private ContentPraiseRepository contentPraiseRepository;

    @Resource
    private ResumeUtil resumeUtil;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 文章存储的目录名
     */
    @Value("${defaultKey.UPLOAD_DIRECTORY}")
    private String UPLOAD_DIRECTORY;

    /**
     * 文章点赞数存储的Key
     */
    @Value("${defaultKey.CONTENT_PRAISE}")
    private String CONTENT_PRAISE;

    @Value("${defaultKey.CONTENT_READNUM}")
    private String CONTENT_READNUM;

    @Override
    public ResultVO getAllContentType() {
        List<String> list = contentTypeRepository.getContentName();

        List<ContentType> list1 = contentTypeRepository.findAll();
        return ResultUtil.success(list1);
    }

    @Override
    public ResultVO saveContentType(ContentType contentType) {
        ContentType result = contentTypeRepository.save(contentType);
        return ResultUtil.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO saveContentInfo(ContentInfo contentInfo) throws FileNotFoundException {
        // 随机生成文章ID
        String contentId = resumeUtil.getResumeIdByRandom(contentInfo.getContent());
        contentInfo.setContentId(contentId);
        // 获取文章摘要
        contentInfo.setSummary(resumeUtil.getSummary(contentInfo.getContent()));
        // 将文章保存以md文件保存，并返回地址
        contentInfo.setContentUrl(resumeUtil.getUploadUrl(contentId,contentInfo.getContent()));
        // 保存信息并返回文章ID,文章类型数量加1
        contentInfoRepository.save(contentInfo);
        contentTypeRepository.updateContentNum(contentInfo.getContentType());
        return ResultUtil.success(contentId);
    }

    @Override
    public ResultVO getContent(String contentId) throws IOException {
        // 获取文章的信息
        ContentInfo contentInfo = contentInfoRepository.findByContentId(contentId);
        if (contentInfo == null){
            return ResultUtil.error(ResultEnum.Content_NO_EXISTS);
        }
        // 从文件获取文章内容
        String path = UPLOAD_DIRECTORY+"/"+contentId+".md";
        File f = new File(path);
        if(!f.exists()){
            throw new MyException(ResultEnum.Content_NO_EXISTS);
        }
        InputStream inputStream = new FileInputStream(f);
        Long filelength = f.length();
        byte[] filecontent = new byte[filelength.intValue()];
        inputStream.read(filecontent);
        inputStream.close();
        String content = new String(filecontent);
        //返回文章内容
        if ("".equals(content) || content == null){
            return ResultUtil.error(ResultEnum.Content_NO_EXISTS);
        }

        contentInfo.setContent(content);
        return ResultUtil.success(contentInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO addPraise(ContentPraise contentPraise) {

        // 判断是否已经点赞过
        if (contentPraiseRepository.existsByContentIdAndAndIp(contentPraise.getContentId(),contentPraise.getIp())){
            throw new MyException(ResultEnum.Praise_EXISTS);
        }
        // 保存点赞历史
        if(contentPraiseRepository.save(contentPraise)==null ){
            throw new MyException(ResultEnum.Praise_ERROR);
        }
        return ResultUtil.success(redisUtil.incrHash(CONTENT_PRAISE,contentPraise.getContentId(), 1));
    }

    @Override
    public ResultVO getPraise(String contentId) {
        Integer praiseNum = getPraiseNum(contentId);
        return ResultUtil.success(praiseNum);
    }


    public Integer getPraiseNum(String contentId){
        Integer praiseNum = 0;
        String num = redisUtil.getHash(CONTENT_PRAISE,contentId);
        // 当Redis中为空时，从数据库中进行统计
        if (num != null){
            praiseNum = Integer.valueOf(num);
        }else{
            praiseNum = contentPraiseRepository.countByContentId(contentId);
        }
        return praiseNum;
    }

    @Override
    public void addReadNum(String contentId) {
        long readNum = redisUtil.incrHash(CONTENT_READNUM,contentId,1);
        if(readNum % 10 == 0){
            contentInfoRepository.addReadNumByContentId(readNum,contentId);
        }
    }

    @Override
    public ResultVO getRecommendationList() {
        return ResultUtil.success(contentInfoRepository.getRecommandtionList());
    }

    @Override
    public ResultVO getReadRankList() {
        return ResultUtil.success(contentInfoRepository.getReadRankList());
    }

    @Override
    public ResultVO getNearContent(String createTime) {
        return ResultUtil.success(contentInfoRepository.getNearContent(createTime));
    }

    @Override
    public ResultVO getContentList(Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        PageRequest pageRequest = PageRequest.of(page-1,5,sort);
        Page<ContentInfo> contentInfoPage = contentInfoRepository.findAll(pageRequest);
        return  ResultUtil.success(contentInfoPage);
    }

    @Override
    public ResultVO getContentListByContentType(String contentType,Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        PageRequest pageRequest = PageRequest.of(page-1,5,sort);
        Page<ContentInfo> contentInfoPage = contentInfoRepository.findAllByContentType(contentType,pageRequest);
        return  ResultUtil.success(contentInfoPage);
    }


}
