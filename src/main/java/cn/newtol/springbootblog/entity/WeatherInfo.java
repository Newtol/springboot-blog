package cn.newtol.springbootblog.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 19:02 2019/6/18
 * @params:
 */

@Data
@Component
public class WeatherInfo {
    /**
     * 城市ID
     */
    private String cityid;
    /**
     * 时间
     */
    private String date;

    /**
     * 星期几
     */
    private String week;

    /**
     * 当前时间
     */
    private String update_time;

    /**
     * 城市
     */
    private String city;

    /**
     * 城市英文
     */
    private String cityEn;

    /**
     * 国家
     */
    private String country;

    /**
     * 国家英文
     */
    private String countryEn;

    /**
     * 天气情况
     */
    private String wea;

    /**
     * 天气图
     */
    private String wea_img;

    /**
     * 当前温度
     */
    private String tem;

    /**
     * 湿度
     */
    private String humidity;

    /**
     * 能见度
     */
    private String visibility;

    /**
     * 空气质量
     */
    private String air;

    /**
     * pm2.5
     */
    private String air_pm25;

    /**
     * 空气质量等级
     */
    private String air_level;

    /**
     * 空气质量描述
     */
    private String air_tips;

    @Override
    public String toString() {
        return "当前日期为：" + date +" "+ week+" " +  update_time +
                ", 城市：" + city +
                ", 天气:" + wea  +
                ", 气温:" + tem  +
                ", 湿度:" + humidity  +
                ", 能见度:" + visibility  +
                ", 空气质量:" + air  +
                ", PM2.5浓度:" + air_pm25 +
                ", 空气质量等级:" + air_level +
                ", 建议:" + air_tips  ;
    }
}
