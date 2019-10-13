package cn.newtol.springbootblog.repository;

import cn.newtol.springbootblog.dao.GuestBookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:44 2019/6/16
 * @params:
 */

public interface GusetBookInfoRepository extends JpaRepository<GuestBookInfo,Integer>{
    /**
     * 按照时间排序获取留言
     * @return
     */
    List<GuestBookInfo> findAllByPIdOrderByCreateTimeDesc(String pId);
}
