package cn.newtol.springbootblog.controller;

import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.services.AboutMeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:44 2019/7/7
 * @params:
 */
@Controller
@RequestMapping("/about")
public class AboutMeController {
    @Resource
    private AboutMeService aboutMeService;
    @GetMapping("")
    public String getAbout(){
        return "blog/about";
    }

    @GetMapping("me")
    @ResponseBody
    public ResultVO getAboutMe() throws IOException {
        return aboutMeService.getAboutMe();
    }

}
