package cn.newtol.springbootblog.controller;

import cn.newtol.springbootblog.utils.ValidCodeImgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 17:45 2019/6/16
 * @params:
 */
@Controller
public class ValidController {
    @Resource
    private ValidCodeImgUtil validCodeImgUtil;

    /**
     * 获取图形验证码
     * @param request
     * @param response
     * @param random:随机数，用于刷新验证码
     */
    @GetMapping("/validCodeImg/{random}")
    public void getValidCodeImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        validCodeImgUtil.getVaildCodeImg(request,response);
    }

}
