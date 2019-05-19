package cn.newtol.springbootmarkdown.controller;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import cn.newtol.springbootmarkdown.dao.ContentType;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.services.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.FileNotFoundException;

/**
 * @Author: 公众号：Newtol
 * @Description: 博客页面路由
 * @Date: Created in 10:54 2019/5/15
 * @params:
 */
@Controller
public class BlogController {
    @Resource
    private BlogService blogService;

    /**
     * 获取博客编辑页面
     * @return
     */
    @GetMapping("/blog/editor")
    public String getEditor(){
        return "blogs/editor";
    }

    /**
     * 获取博客首页
     * @return 返回首页
     */
    @GetMapping("/index")
    public String getIndex(){
        return "blogs/index";
    }

    /**
     * 获取文章种类列表
     * @return 返回所有文章种类和各自的数量
     */
    @GetMapping("/blog/ContentType")
    @ResponseBody
    public ResultVO getContentType(){
        return blogService.getAllContentType();
    }

    /**
     * 添加文章种类，返回是否成功
     * @param contentType
     * @return
     */
    @PostMapping("/ContentType")
    @ResponseBody
    public ResultVO saveContentType(@Valid  ContentType contentType){
        return blogService.saveContentType(contentType);
    }


    /**
     * 保存博客
     * @param contentInfo
     * @return 返回博客ID
     * @throws FileNotFoundException
     */
    @PostMapping("/blog/saveContent")
    @ResponseBody
    public ResultVO saveContent(@Valid ContentInfo contentInfo) throws FileNotFoundException {
        return blogService.saveContentInfo(contentInfo);
    }

    /**
     * 测试接口
     * @return
     */
    @GetMapping("/blogs/info")
    public String aboutMe(@RequestParam String contentId){
        return "blogs/info";
    }
}
