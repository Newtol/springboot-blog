package cn.newtol.springbootmarkdown.services.impl;

import cn.newtol.springbootmarkdown.dao.ResumeInfo;
import cn.newtol.springbootmarkdown.dao.ResumeTemplate;
import cn.newtol.springbootmarkdown.entity.HttpClientResult;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.entity.ResumeDTO;
import cn.newtol.springbootmarkdown.myEnum.ResultEnum;
import cn.newtol.springbootmarkdown.repository.ResumeRepository;
import cn.newtol.springbootmarkdown.repository.ResumeTemplateRepository;
import cn.newtol.springbootmarkdown.services.ResumeService;
import cn.newtol.springbootmarkdown.utils.HttpClientUtil;
import cn.newtol.springbootmarkdown.utils.HttpServletUtil;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import cn.newtol.springbootmarkdown.utils.ResumeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:31 2019/5/1
 * @params:
 */
@Service
@Component
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Resource
    private ResumeUtil resumeUtil;


    @Resource
    private ResumeTemplateRepository resumeTemplateRepository;

    @Override
    public ResultVO saveResume(HttpServletRequest httpServletRequest, ResumeDTO resumeDTO) throws Exception {
        ResumeInfo resumeInfo = new ResumeInfo();

        // 初始化
        resumeInfo.setIp(HttpServletUtil.getIpAdrress(httpServletRequest));
        resumeInfo.setTitle(resumeDTO.getTitle());
        resumeInfo.setSummary(resumeUtil.getSummary(resumeDTO.getContent()));
        resumeInfo.setType(1);
        String resumeUrl = resumeUtil.getUploadUrl(resumeDTO);
        resumeInfo.setResumeUrl(resumeUrl);
        // 保存到数据库
        ResumeInfo result = resumeRepository.save(resumeInfo);

        // 转换为pdf，并返回pdf文件地址
        String pdfUrl = downloadResumeAsPdf(resumeUrl);

        if ("".equals(pdfUrl) || pdfUrl.isEmpty()){
            return ResultUtil.error(ResultEnum.Transfer_ERROR);
        }else {
            return ResultUtil.success("http://www.mdtr2pdf.com/"+pdfUrl);
        }
    }


    @Override
    public ResultVO getResumeTemplate(String resumeId) {
        return  ResultUtil.success(resumeTemplateRepository.getResumeContentByResumeId(resumeId));
    }


    @Override
    public ResultVO addResumeTemplate(ResumeTemplate resumeTemplate) {
        // 使用文章名作为盐值生成文章的ID
        resumeTemplate.setResumeId(resumeUtil.getResumeIdByRandom(resumeTemplate.getResumeName()));
        // 模板保存至数据库
        ResumeTemplate result =  resumeTemplateRepository.save(resumeTemplate);
        return ResultUtil.success();
    }

    @Override
    public String downloadResumeAsPdf(String fileUrl) throws Exception {
        // 封装请求的参数
        Map<String,String> map = new HashMap<>();
        String k = String.valueOf(System.currentTimeMillis());
        map.put("d", k);
        map.put("m", String.valueOf(k.charAt(7)));
        // 请求接口，并上传文件
        HttpClientResult httpClientResult = HttpClientUtil.doPost("http://www.mdtr2pdf.com/upload",map,"upload_file",fileUrl);

        // 返回pdf文件地址
        return httpClientResult.getContent();
    }

    @Override
    public ResultVO getResumeTemplateTypeList() {
        return  ResultUtil.success(resumeTemplateRepository.getResumeTemplateTypeList());

    }
}
