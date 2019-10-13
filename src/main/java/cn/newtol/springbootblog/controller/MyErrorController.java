package cn.newtol.springbootblog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 公众号：Newtol
 * @Description: 错误展示页面
 * @Date: Created in 11:12 2019/5/15
 * @params:
 */


@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        HttpStatus status = this.getStatus(request);
        if(status.value() == 404){
            return "error/404"; // 返回404页面
        }else if(status.value() == 500){
            return "error/500"; // 返回 500 页面
        }
        return "error/error"; // 返回其它错误的页面
    }

    @Override
    public String getErrorPath() {
        return "error"; // 如果异常则请求到此url (这里是请求到/error)，可以自定义
    }

    /**
     * 获取状态码
     * @param request
     * @return
     */
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode.intValue());
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
}