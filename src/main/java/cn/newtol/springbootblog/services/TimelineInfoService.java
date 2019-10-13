package cn.newtol.springbootblog.services;

import cn.newtol.springbootblog.dao.TimelineInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:21 2019/6/20
 * @params:
 */
@Service
public interface TimelineInfoService {
    /**
     * 保存时间轴信息
     * @param timelineInfo
     * @return
     */
    ResultVO saveTimelineInfo(TimelineInfo timelineInfo);


    /**
     * 获取时间轴信息
     * @param page：页数
     * @return
     */
    ResultVO getTimelineInfo(Integer page);
}
