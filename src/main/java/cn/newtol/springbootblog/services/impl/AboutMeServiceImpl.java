package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.exceptions.MyException;
import cn.newtol.springbootblog.myEnum.ResultEnum;
import cn.newtol.springbootblog.services.AboutMeService;
import cn.newtol.springbootblog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:45 2019/7/7
 * @params:
 */
@Service
public class AboutMeServiceImpl implements AboutMeService {
    /**
     * 文章存储的目录名
     */
    @Value("${defaultKey.UPLOAD_DIRECTORY}")
    private String UPLOAD_DIRECTORY;

    @Override
    public ResultVO getAboutMe() throws IOException {
        // 从文件获取文章内容
        String path = UPLOAD_DIRECTORY+"/"+"aboutMe"+".md";
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
