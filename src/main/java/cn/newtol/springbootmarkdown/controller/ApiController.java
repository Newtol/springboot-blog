package cn.newtol.springbootmarkdown.controller;

import cn.newtol.springbootmarkdown.dao.SoupInfo;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.services.ApiService;
import cn.newtol.springbootmarkdown.services.WeatherService;
import cn.newtol.springbootmarkdown.utils.HttpServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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


    @GetMapping("/weather")
    @ResponseBody
    public ResultVO getWeatherByIp(HttpServletRequest httpServletRequest) throws Exception {
        String ip = HttpServletUtil.getIpAdrress(httpServletRequest);
        return weatherService.getWeatherByIp(ip);
    }
}
