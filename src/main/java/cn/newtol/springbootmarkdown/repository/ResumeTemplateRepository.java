package cn.newtol.springbootmarkdown.repository;

import cn.newtol.springbootmarkdown.dao.ResumeTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:27 2019/5/3
 * @params:
 */
public interface ResumeTemplateRepository extends JpaRepository<ResumeTemplate,Integer> {
    @Query(value = "SELECT resume_content from resume_template where resume_id = ? ",nativeQuery = true)
    String getResumeContentByResumeId(String resumeId);
}
