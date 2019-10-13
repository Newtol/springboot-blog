package cn.newtol.springbootblog.services;

import cn.newtol.springbootblog.dao.ResumeTemplate;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.entity.ResumeDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
    String downloadResumeAsPdf(String fileUrl,String fileName,String fileType) throws Exception;

    /**
     * 得到所有的模板简历
     * @return 返回简历类型ResumeTpe、模板简历ID
     */
    ResultVO getResumeTemplateTypeList();
}
