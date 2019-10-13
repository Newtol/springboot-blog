package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.dao.TimelineInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.repository.TimelineInfoRepository;
import cn.newtol.springbootblog.services.TimelineInfoService;
import cn.newtol.springbootblog.utils.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:33 2019/6/20
 * @params:
 */

@Service
@Component
public class TimelineInfoServiceImpl implements TimelineInfoService{
    @Resource
    private TimelineInfoRepository timelineInfoRepository;
    @Override
    public ResultVO saveTimelineInfo(TimelineInfo timelineInfo) {
        return ResultUtil.success(timelineInfoRepository.save(timelineInfo));
    }

    @Override
    public ResultVO getTimelineInfo(Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        PageRequest pageRequest = PageRequest.of(page-1,5,sort);
        Page<TimelineInfo> timelineInfos = timelineInfoRepository.findAll(pageRequest);
        return ResultUtil.success(timelineInfos);
    }
}
