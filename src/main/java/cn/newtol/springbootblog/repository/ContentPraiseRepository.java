package cn.newtol.springbootblog.repository;

import cn.newtol.springbootblog.dao.ContentPraise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 17:19 2019/6/13
 * @params:
 */
@Service
@Repository
public interface ContentPraiseRepository extends JpaRepository<ContentPraise,Integer> {
    /**
     * 判断已经点赞
     * @param contentId：文章地址
     * @param ip：用户IP地址，用于确定未注册用户的唯一性
     * @return boolean
     */
    Boolean existsByContentIdAndAndIp(String contentId,String ip);

    /**
     * 统计文章的点赞数
     * @param contentId：文章ID
     * @return 文章点赞数
     */
    Integer countByContentId(String contentId);
}
