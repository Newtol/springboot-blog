package cn.newtol.springbootblog.repository;

import cn.newtol.springbootblog.dao.FriendUrlInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:26 2019/6/20
 * @params:
 */
@Repository
@Service
public interface FriendUrlRepository extends JpaRepository<FriendUrlInfo,Integer> {

}
