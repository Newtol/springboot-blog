package cn.newtol.springbootmarkdown.repository;

import cn.newtol.springbootmarkdown.dao.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 11:55 2019/5/17
 * @params:
 */
@Service
@Repository
public interface ContentTypeRepository extends JpaRepository<ContentType,Integer> {
    /**
     * 获取文章类型
     * @return
     */
    @Query(value = "SELECT name FROM content_type",nativeQuery = true)
    List<String> getContentName();



//    /**
//     * 更新文章数目
//     * @param content_type
//     * @return
//     */
//    @Modifying
//    @Query(value = "UPDATE content_type SET num = num+1 where content_type = ? ",nativeQuery = true)
//    Boolean updateContentNum(String content_type);



}
