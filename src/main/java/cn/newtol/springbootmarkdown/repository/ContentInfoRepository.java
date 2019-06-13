package cn.newtol.springbootmarkdown.repository;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:52 2019/5/17
 * @params:
 */
public interface ContentInfoRepository extends JpaRepository<ContentInfo,Integer> {

    /**
     * 获取文章内容
     * @param contentId：文章ID
     * @return
     */
    ContentInfo findByContentId(String contentId);

    /**
     * 更新访问量
     * @param readNum
     * @param contentId
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE content_info SET read_num = ?1  where content_id = ?2 ",nativeQuery = true)
    void addReadNumByContentId(Long readNum,String contentId);

    @Query(value = "SELECT content_id,update_time,title FROM content_info WHERE is_view =0 ORDER BY update_time desc limit 0,5",nativeQuery = true)
    List<Map> getRecommandtionList();

}
