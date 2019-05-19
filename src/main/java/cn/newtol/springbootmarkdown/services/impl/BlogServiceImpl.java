package cn.newtol.springbootmarkdown.services.impl;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import cn.newtol.springbootmarkdown.dao.ContentType;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.exceptions.MyException;
import cn.newtol.springbootmarkdown.repository.ContentInfoRepository;
import cn.newtol.springbootmarkdown.repository.ContentTypeRepository;
import cn.newtol.springbootmarkdown.services.BlogService;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import cn.newtol.springbootmarkdown.utils.ResumeUtil;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.FileNotFoundException;
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
    private ResumeUtil resumeUtil;

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
        contentInfo.setContentUrl(resumeUtil.getUploadUrl(contentInfo.getTitle(),contentInfo.getContent()));
        contentInfoRepository.save(contentInfo);


//        return ResultUtil.success(contentId);
        return ResultUtil.success("3EF7968534EEEBB8668F0239B9B97E6B.md");
    }


}
