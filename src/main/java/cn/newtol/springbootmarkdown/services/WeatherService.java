package cn.newtol.springbootmarkdown.services;

import cn.newtol.springbootmarkdown.entity.ResultVO;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description: 天气服务
 * @Date: Created in 19:21 2019/6/18
 * @params:
 */
@Service
public interface WeatherService {
    /**
     * 通过IP地址获取天气信息
     * @param ip
     * @return
     */
    ResultVO getWeatherByIp(String ip) throws Exception;
}
