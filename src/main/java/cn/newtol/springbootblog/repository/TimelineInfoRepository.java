package cn.newtol.springbootblog.repository;

import cn.newtol.springbootblog.dao.TimelineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:26 2019/6/20
 * @params:
 */

@Repository
@Service
public interface TimelineInfoRepository extends JpaRepository<TimelineInfo,Integer> {

}
