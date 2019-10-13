package cn.newtol.springbootblog.controller;
import cn.newtol.springbootblog.dao.ResumeTemplate;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.entity.ResumeDTO;
import cn.newtol.springbootblog.services.ResumeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:10 2019/4/28
 * @params:
 */

@Controller
public class ResumeController {
    @Resource
    private ResumeService resumeService;

    /**
     * 文章存储的目录名
     */
    @Value("${defaultKey.UPLOAD_DIRECTORY}")
    private String UPLOAD_DIRECTORY;

    /**
     * 获取编辑页面
     * @return 返回简历编辑页面
     */
    @GetMapping("/editor")
    public String getEditor(){
        return "resume/editor";
    }

    /**
     * 简历上传界面
     * @param httpServletRequest 获取用户IP地址等信息
     * @param resumeDTO 简历内容
     * @return 是否填写成功
     */
    @PostMapping("/editor/myResume")
    @ResponseBody
    public ResultVO updateResume(HttpServletRequest httpServletRequest, @Valid ResumeDTO resumeDTO) throws Exception {
        return resumeService.saveResume(httpServletRequest,resumeDTO);
    }

    /**
     * 获取模板简历
     * @return 返回模板简历内容
     */
    @GetMapping("/editor/getTemplateResume/{resumeId}")
    @ResponseBody
    public ResultVO getTemplateResume(@PathVariable String resumeId){
        return resumeService.getResumeTemplate(resumeId);
    }


    /**
     * 模板编辑页面
     * @return
     */
    @GetMapping("/editorResumeTemplate")
    public String getEditorResumeTemplate(){
        return "resume/editorResumeTemplate";
    }

    /**
     * 增加简历模板
     */
    @PostMapping("/addResumeTemplate")
    @ResponseBody
    public ResultVO add(@Valid ResumeTemplate resumeTemplate){
        return resumeService.addResumeTemplate(resumeTemplate);
    }

    /**
     * 获取简历模板列表
     * @return 返回所有简历模板的id和简历名字
     */
    @GetMapping("resumeTemplate/lists")
    @ResponseBody
    public ResultVO getResumeTemplateTypeList(){
        return resumeService.getResumeTemplateTypeList();
    }

    /**
     * 测试接口
     * @return
     */
    @GetMapping("/download/{filename}")
    public void download(HttpServletResponse res,@PathVariable String filename){
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + filename);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(UPLOAD_DIRECTORY+"/"+filename)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
