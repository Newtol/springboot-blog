package cn.newtol.springbootblog.services.impl;

import cn.newtol.springbootblog.dao.FriendUrlInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.repository.FriendUrlRepository;
import cn.newtol.springbootblog.services.FriendUrlService;
import cn.newtol.springbootblog.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:24 2019/6/20
 * @params:
 */

@Service
public class FriendUrlServiceImpl implements FriendUrlService {

    @Resource
    private FriendUrlRepository friendUrlRepository;

    @Override
    public ResultVO svaeFriendUrlInfo(FriendUrlInfo friendUrlInfo) {
        return ResultUtil.success(friendUrlRepository.save(friendUrlInfo));
    }

    @Override
    public ResultVO getFriendUrlInfo() {
        return ResultUtil.success(friendUrlRepository.findAll());
    }
}
