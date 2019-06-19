package cn.newtol.springbootmarkdown.services.impl;

import cn.newtol.springbootmarkdown.entity.HttpClientResult;
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.entity.WeatherInfo;
import cn.newtol.springbootmarkdown.services.WeatherService;
import cn.newtol.springbootmarkdown.utils.HttpClientUtil;
import cn.newtol.springbootmarkdown.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 19:22 2019/6/18
 * @params:
 */
@Service
public class WeatherServiceImpl implements WeatherService{
    @Value("${defaultKey.WEATHER_URL}")
    private String WEATHER_URL;
    @Override
    public ResultVO getWeatherByIp(String ip) throws Exception {
        // 封装请求参数
        Map<String,String> map= new HashMap<>(2);
        map.put("version","v6");
        map.put("ip",ip);
        HttpClientResult httpClientResult = HttpClientUtil.doGet(WEATHER_URL,map);
        WeatherInfo weatherInfo = JSONObject.parseObject(httpClientResult.getContent(), WeatherInfo.class);
        return ResultUtil.success(weatherInfo.toString());
    }
}
