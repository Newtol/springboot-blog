package cn.newtol.springbootmarkdown.utils;

import cn.newtol.springbootmarkdown.entity.ResumeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:56 2019/5/2
 * @params:
 */
@Service
@Component
public class ResumeUtil {
    /**
     * 文章存储的目录名
     */
    @Value("${defaultKey.UPLOAD_DIRECTORY}")
    private String UPLOAD_DIRECTORY;
    /**
     * 获得文章摘要
     * @param content 文章内容
     * @return 返回文章标题的前100个字节作为摘要
     * @throws FileNotFoundException
     */
    public String getSummary(String content)  {
        StringBuilder summary = new StringBuilder("文章摘要：");
        // 匹配所有标题，作为文章摘要
//        String pattern = "(#+)(.*)";

        // 匹配一、二级标题作为摘要
        String pattern = "^(#{1,2})\\s(.*)";
        Pattern p= Pattern.compile(pattern,Pattern.MULTILINE);
        Matcher m=p.matcher(content);
        while(m.find() && summary.length()<100) {
            summary.append(m.group(2)).append(",");
        }
        return summary.toString();
    }

    /**
     * 上传文章
     * @param resumeDTO 文章内容
     * @return 返回文章的url
     * @throws FileNotFoundException
     */
    public String getUploadUrl(String title,String content) throws FileNotFoundException{
        String atitle = EncryptUtil.MD5(title);
//        String content = resumeDTO.getContent();
        // 判断文件夹是否已经创建
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        // 上传文件
        String path = UPLOAD_DIRECTORY+"/"+title+".md";
        File f = new File(path);
        PrintWriter pw = new PrintWriter(f);
        pw.print(content);
        pw.close();
        return path;
    }

    /**
     * 获取文件Id
     * @param str 文件名
     * @return 文件Id
     */
    public String getResumeIdByRandom(String str){
        long current=System.currentTimeMillis();
        return EncryptUtil.MD5(str+current+"myWebSites");
    }
}
