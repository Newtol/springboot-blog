package cn.newtol.springbootblog.controller;

import cn.newtol.springbootblog.dao.TimelineInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.exceptions.MyException;
import cn.newtol.springbootblog.myEnum.ResultEnum;
import cn.newtol.springbootblog.services.TimelineInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:13 2019/6/20
 * @params:
 */

@Controller
public class TimelineController {

    @GetMapping("/share")
    public String getShare(){
        return "blog/share";
    }

    @Resource
    private TimelineInfoService timelineInfoService;

    /**
     * 保存时间轴信息
     * @param timelineInfo
     * @return
     */
    @PostMapping("/timeline")
    @ResponseBody
    public ResultVO saveTime(@Valid TimelineInfo timelineInfo){
        return timelineInfoService.saveTimelineInfo(timelineInfo);
    }

    /**
     * 获取时间轴信息
     * @param page
     * @return
     */
    @GetMapping("timeline")
    @ResponseBody
    public ResultVO getTimeline(@RequestParam Integer page){
        if(page==null || "".equals(page)){
            throw new MyException(ResultEnum.LACK_PARAMETER);
        }
        return timelineInfoService.getTimelineInfo(page);
    }


}
