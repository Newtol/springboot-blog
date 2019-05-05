package cn.newtol.springbootmarkdown.services;

import cn.newtol.springbootmarkdown.dao.ResumeTemplate;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.entity.ResumeDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:27 2019/5/1
 * @params:
 */
@Service
public interface ResumeService {
    /**
     * 保存简历
     * @param httpServletRequest
     * @param resumeDTO：
     * @return
     */
    ResultVO saveResume(HttpServletRequest httpServletRequest, ResumeDTO resumeDTO) throws Exception;

    /**
     * 获取模板简历
     * @param resumeId 简历名
     * @return
     */
    ResultVO getResumeTemplate(String resumeId);

    /**
     * 增加简历模板
     * @param resumeTemplate 简历内容
     * @return
     */
    ResultVO addResumeTemplate(ResumeTemplate resumeTemplate);

    /**
     * 将简历下载为pdf
     * @param fileUrl
     * @return
     */
    String downloadResumeAsPdf(String fileUrl) throws Exception;
}
