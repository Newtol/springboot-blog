package cn.newtol.springbootmarkdown.services.impl;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import cn.newtol.springbootmarkdown.dao.ContentPraise;
import cn.newtol.springbootmarkdown.dao.ContentType;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.exceptions.MyException;
import cn.newtol.springbootmarkdown.myEnum.ResultEnum;
import cn.newtol.springbootmarkdown.repository.ContentInfoRepository;
import cn.newtol.springbootmarkdown.repository.ContentPraiseRepository;
import cn.newtol.springbootmarkdown.repository.ContentTypeRepository;
import cn.newtol.springbootmarkdown.services.BlogService;
import cn.newtol.springbootmarkdown.utils.RedisUtil;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import cn.newtol.springbootmarkdown.utils.ResumeUtil;
import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        return ResultUtil.success(list);
    }

    @Override
    public ResultVO saveContentType(ContentType contentType) {
        ContentType result = contentTypeRepository.save(contentType);
        return ResultUtil.success();
    }

    @Override
    public ResultVO saveContentInfo(ContentInfo contentInfo) throws FileNotFoundException {
        String contentId = resumeUtil.getResumeIdByRandom(contentInfo.getContent());
        contentInfo.setContentId(contentId);
        contentInfo.setSummary(resumeUtil.getSummary(contentInfo.getContent()));
        contentInfo.setContentUrl(resumeUtil.getUploadUrl(contentId,contentInfo.getContent()));
        contentInfoRepository.save(contentInfo);
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

    @Transactional
    @Override
    public ResultVO addPraise(ContentPraise contentPraise) {
        System.out.println(contentPraise.getContentId());
        if (contentPraiseRepository.existsByContentIdAndAndIp(contentPraise.getContentId(),contentPraise.getIp())){
            throw new MyException(ResultEnum.Praise_EXISTS);
        }
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


}
