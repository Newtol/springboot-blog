package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.dao.SoupInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.exceptions.MyException;
import cn.newtol.springbootblog.myEnum.ResultEnum;
import cn.newtol.springbootblog.repository.SoupInfoRepository;
import cn.newtol.springbootblog.services.ApiService;
import cn.newtol.springbootblog.utils.RedisUtil;
import cn.newtol.springbootblog.utils.ResultUtil;
import cn.newtol.springbootblog.utils.ResumeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:49 2019/6/19
 * @params:
 */
@Service
public class ApiServiceImpl implements ApiService {
    @Resource
    private SoupInfoRepository soupInfoRepository;

    @Resource
    private ResumeUtil resumeUtil;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 毒鸡汤ID存储的set的KEY
     */
    @Value("${defaultKey.SOUP_KEY}")
    private String SOUP_KEY;

    /**
     * 文章存储的目录名
     */
    @Value("${defaultKey.UPLOAD_DIRECTORY}")
    private String UPLOAD_DIRECTORY;

    @Override
    public ResultVO getSoup() {
        String id = redisUtil.srandMember(SOUP_KEY);
        return ResultUtil.success(soupInfoRepository.findByContentId(id));
    }

    @Override
    public ResultVO saveSoup(SoupInfo soupInfo) {
        // 随机获取ID
        String id = resumeUtil.getResumeIdByRandom(soupInfo.getContent());
        // 设置ID,并存在Redis的set中
        soupInfo.setContentId(id);
        redisUtil.sadd(SOUP_KEY,id);
        // 保存至数据库并返回
        return ResultUtil.success(soupInfoRepository.save(soupInfo));
    }

    @Override
    public ResultVO getOpenApiContent() throws IOException {
        // 从文件获取文章内容
        String path = UPLOAD_DIRECTORY+"/"+"openApi"+".md";
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
        return ResultUtil.success(content);

    }
}
