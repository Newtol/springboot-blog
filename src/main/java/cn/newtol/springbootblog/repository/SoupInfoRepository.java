package cn.newtol.springbootblog.repository;

import cn.newtol.springbootblog.dao.SoupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:47 2019/6/19
 * @params:
 */
@Repository
@Service
public interface SoupInfoRepository  extends JpaRepository<SoupInfo,Integer>{
    /**
     * 通过ID查询毒鸡汤内容
     * @param contentId
     * @return
     */
    SoupInfo findByContentId(String contentId);
}
