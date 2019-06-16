package cn.newtol.springbootmarkdown.controller;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import cn.newtol.springbootmarkdown.dao.ContentPraise;
import cn.newtol.springbootmarkdown.dao.ContentType;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.services.BlogService;
import cn.newtol.springbootmarkdown.utils.HttpServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: 公众号：Newtol
 * @Description: 博客页面路由
 * @Date: Created in 10:54 2019/5/15
 * @params:
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;

    /**
     * 获取博客编辑页面
     * @return
     */
    @GetMapping("/editor")
    public String getEditor(){
        return "blog/editor";
    }

    /**
     * 获取博客首页
     * @return 返回首页
     */
    @GetMapping("/index")
    public String getIndex(){
        return "blog/index";
    }

    /**
     * 获取文章种类列表
     * @return 返回所有文章种类和各自的数量
     */
    @GetMapping("/contentType")
    @ResponseBody
    public ResultVO getContentType(){
        return blogService.getAllContentType();
    }

    /**
     * 添加文章种类，返回是否成功
     * @param contentType
     * @return
     */
    @PostMapping("/contentType")
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
    @PostMapping("/content")
    @ResponseBody
    public ResultVO saveContent(@Valid ContentInfo contentInfo) throws FileNotFoundException {
        return blogService.saveContentInfo(contentInfo);
    }

    /**
     * 文章详情页
     * @param contentId:文章ID
     * @return 返回详情页
     */
    @GetMapping("/info")
    public String aboutMe(@RequestParam String contentId){
        return "blog/info";
    }

    /**
     * 文章点赞
     * @param contentPraise
     * @return 返回文章ID和当前点赞数
     */
    @PostMapping("/praise")
    @ResponseBody
    public ResultVO addPraise(HttpServletRequest httpServletRequest,@Valid ContentPraise contentPraise){
        contentPraise.setIp(HttpServletUtil.getIpAdrress(httpServletRequest));
        return blogService.addPraise(contentPraise);
    }

    /**
     * 获取文章点赞数
     * @param contentId：文章ID
     * @return:返回文章的点赞数
     */
    @GetMapping("/praise")
    @ResponseBody
    public ResultVO getPraise(@RequestParam String contentId){
        return blogService.getPraise(contentId);
    }

    /**
     * 获取文章内容
     */
    @GetMapping("/content")
    @ResponseBody
    public ResultVO getContentInfo(@RequestParam String contentId) throws IOException {
        blogService.addReadNum(contentId);
        return blogService.getContent(contentId);
    }

    /**
     * 获取站长推荐
     * @return：最近创建的前5条文章
     */
    @GetMapping("/recommendationList")
    @ResponseBody
    public ResultVO getRecommendationList(){
        return blogService.getRecommendationList();
    }

    /**
     * 获取阅读排行榜
     * @return
     */
    @GetMapping("/readRankList")
    @ResponseBody
    public ResultVO getReadRankList(){
        return blogService.getReadRankList();
    }

    /**
     * 获取上一篇、下一篇文章
     * @param createTime：当前文章的创作时间
     * @return：返回文章ID、文章标题
     */
    @GetMapping("/nearContent")
    @ResponseBody
    public ResultVO getNearContent(@RequestParam String createTime){
        return blogService.getNearContent(createTime);
    }

    /**
     * 根据时间顺序获取文章列表
     * @param page
     * @return
     */
    @GetMapping("/contentList")
    @ResponseBody
    public ResultVO getContentList(@RequestParam Integer page){
        return blogService.getContentList(page);
    }


    @GetMapping("/list")
    public String getListIndex(){
        return "blog/list";
    }
    /**
     * 根据文章类型获取文章列表
     * @param contentType：文章类型
     * @param page：页数
     * @return
     */
    @GetMapping("contentList/{contentType}")
    @ResponseBody
    public ResultVO getContentListByContentType(@PathVariable String contentType,@RequestParam Integer page){
        return blogService.getContentListByContentType(contentType,page);
    }

    @GetMapping("/share")
    public String getShare(){
        return "blog/share";
    }

    @GetMapping("/gbook")
    public String getGbook(){
        return "blog/gbook";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "blog/about";
    }

    @GetMapping("/game")
    public String getGame(){
        return "blog/infopic";
    }

}
