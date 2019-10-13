package cn.newtol.springbootblog.controller;

import cn.newtol.springbootblog.dao.SoupInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.services.ApiService;
import cn.newtol.springbootblog.services.WeatherService;
import cn.newtol.springbootblog.utils.HttpServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:45 2019/6/19
 * @params:
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Resource
    private ApiService apiService;
    @GetMapping("/soup")
    @ResponseBody
    public ResultVO getSoup(){
        return apiService.getSoup();
    }

    /**
     * 保存毒鸡汤
     * @return
     */
    @PostMapping("/soup")
    @ResponseBody
    public ResultVO saveSoup(@Valid SoupInfo soupInfo){
        return apiService.saveSoup(soupInfo);
    }

    @Resource
    private WeatherService weatherService;


    /**
     * 获取天气预报接口
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @GetMapping("/weather")
    @ResponseBody
    public ResultVO getWeatherByIp(HttpServletRequest httpServletRequest) throws Exception {
        String ip = HttpServletUtil.getIpAdrress(httpServletRequest);
        return weatherService.getWeatherByIp(ip);
    }


    /**
     * 获取开发API文档
     * @return
     */
    @GetMapping("/")
    @ResponseBody
    public ResultVO getOpenApi() throws IOException {
        return apiService.getOpenApiContent();
    }


}
