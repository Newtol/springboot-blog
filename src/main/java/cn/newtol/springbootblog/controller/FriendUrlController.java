package cn.newtol.springbootblog.controller;

import cn.newtol.springbootblog.dao.FriendUrlInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.services.FriendUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:22 2019/6/20
 * @params:
 */
@Controller
public class FriendUrlController {

    @Resource
    public FriendUrlService friendUrlService;

    @PostMapping("/friendUrl")
    @ResponseBody
    public ResultVO saveFriendInfoUrl(@Valid FriendUrlInfo friendUrlInfo){
        return friendUrlService.svaeFriendUrlInfo(friendUrlInfo);
    }

    @GetMapping("/friendUrl")
    @ResponseBody
    public ResultVO getFriendInfoUrl(){
        return friendUrlService.getFriendUrlInfo();
    }
}
