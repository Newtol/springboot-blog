package cn.newtol.springbootmarkdown.repository;

import cn.newtol.springbootmarkdown.dao.ContentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * 获取最新的五条文章
     * @return：文章ID、更新时间、标题
     */
    @Query(value = "SELECT content_id,update_time,title FROM content_info WHERE is_view =0 ORDER BY update_time desc limit 0,5",nativeQuery = true)
    List<Map> getRecommandtionList();

    /**
     * 获取阅读量最高的5条数据
     * @return
     */
    @Query(value = "SELECT content_id,read_num,title FROM content_info WHERE is_view =0 ORDER BY read_num desc limit 0,5",nativeQuery = true)
    List<Map> getReadRankList();


    /**
     * 查询上一篇、下一篇
     * @param create_time：当前文章创作时间
     * @return
     */
    @Query(value = " (select content_id,title from content_info where create_time < ?1 order by id desc limit 1) union all (select id,title from content_info where create_time > ?1 order by id asc limit 1)",nativeQuery = true)
    List<Map> getNearContent(String create_time);


    /**
     * 获取当前所有文章
     * @param contentType
     * @param pageable
     * @return
     */
    Page<ContentInfo> findAllByContentType(String contentType,Pageable pageable);
}
