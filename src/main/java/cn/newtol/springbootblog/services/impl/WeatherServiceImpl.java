package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.entity.HttpClientResult;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.entity.WeatherInfo;
import cn.newtol.springbootblog.services.WeatherService;
import cn.newtol.springbootblog.utils.HttpClientUtil;
import cn.newtol.springbootblog.utils.ResultUtil;
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
        map.put("appid","19888371");
        map.put("appsecret","gn9Hze8e");
        HttpClientResult httpClientResult = HttpClientUtil.doGet(WEATHER_URL,map);
        WeatherInfo weatherInfo = JSONObject.parseObject(httpClientResult.getContent(), WeatherInfo.class);
        return ResultUtil.success(weatherInfo.toString());
    }
}
