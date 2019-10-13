package cn.newtol.springbootblog.services;

import cn.newtol.springbootblog.dao.FriendUrlInfo;
import cn.newtol.springbootblog.entity.ResultVO;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:24 2019/6/20
 * @params:
 */
@Service
public interface FriendUrlService {
    /**
     * 保存友链
     * @param friendUrlInfo
     * @return
     */
    ResultVO svaeFriendUrlInfo(FriendUrlInfo friendUrlInfo);

    /**
     * 获取友链
     * @return
     */
    ResultVO getFriendUrlInfo();
}
