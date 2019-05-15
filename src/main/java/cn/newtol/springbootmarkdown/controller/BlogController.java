package cn.newtol.springbootmarkdown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 公众号：Newtol
 * @Description: 博客页面路由
 * @Date: Created in 10:54 2019/5/15
 * @params:
 */
@Controller
public class BlogController {
    @GetMapping("/index")
    public String getIndex(){
        return "blogs/index";
    }
}
