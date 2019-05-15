package cn.newtol.springbootmarkdown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 公众号：Newtol
 * @Description: 错误展示页面
 * @Date: Created in 11:12 2019/5/15
 * @params:
 */
@Controller
public class ErrorController {
    @GetMapping("/error")
    public String get(){
        return "blogs/index";
    }
}
