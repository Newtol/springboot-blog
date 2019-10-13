package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.dao.ResumeInfo;
import cn.newtol.springbootblog.dao.ResumeTemplate;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.entity.ResumeDTO;
import cn.newtol.springbootblog.myEnum.ResultEnum;
import cn.newtol.springbootblog.repository.ResumeRepository;
import cn.newtol.springbootblog.repository.ResumeTemplateRepository;
import cn.newtol.springbootblog.services.ResumeService;
import cn.newtol.springbootblog.utils.HttpServletUtil;
import cn.newtol.springbootblog.utils.ResultUtil;
import cn.newtol.springbootblog.utils.ResumeUtil;
import com.youbenzi.md2.export.FileFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

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

    /**
     * 文章存储的目录名
     */
    @Value("${defaultKey.UPLOAD_DIRECTORY}")
    private String UPLOAD_DIRECTORY;

    @Override
    public ResultVO saveResume(HttpServletRequest httpServletRequest, ResumeDTO resumeDTO) throws Exception {
        ResumeInfo resumeInfo = new ResumeInfo();

        // 初始化
        resumeInfo.setIp(HttpServletUtil.getIpAdrress(httpServletRequest));
        resumeInfo.setTitle(resumeDTO.getTitle());
        resumeInfo.setSummary(resumeUtil.getSummary(resumeDTO.getContent()));
        resumeInfo.setType(1);
        String resumeUrl = resumeUtil.getUploadUrl(resumeDTO.getTitle(),resumeDTO.getContent());
        resumeInfo.setResumeUrl(resumeUrl);
        // 保存到数据库
        ResumeInfo result = resumeRepository.save(resumeInfo);

        // 转换为pdf，并返回pdf文件地址
        String uploadUrl = downloadResumeAsPdf(resumeUrl,resumeDTO.getTitle(),"pdf");

        if ("".equals(uploadUrl) || uploadUrl.isEmpty()){
            return ResultUtil.error(ResultEnum.Transfer_ERROR);
        }else {
            return ResultUtil.success(uploadUrl);
        }
    }

    @Override
    public String downloadResumeAsPdf(String fileUrl,String fileName,String fileType) throws Exception {
        System.out.println(fileUrl);
        String uploadUrl = fileName+"."+fileType;
        FileFactory.produce(new File(fileUrl),UPLOAD_DIRECTORY+"/"+uploadUrl);
        return uploadUrl;
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
    public ResultVO getResumeTemplateTypeList() {
        return  ResultUtil.success(resumeTemplateRepository.getResumeTemplateTypeList());

    }
}
