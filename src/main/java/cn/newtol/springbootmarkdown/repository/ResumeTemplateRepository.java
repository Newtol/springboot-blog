package cn.newtol.springbootmarkdown.repository;

import cn.newtol.springbootmarkdown.dao.ResumeTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:27 2019/5/3
 * @params:
 */
@Service
@Repository
public interface ResumeTemplateRepository extends JpaRepository<ResumeTemplate,Integer> {
    /**
     * 根据简历ID查询简历类型
     * @param resumeId 简历ID
     * @return 简历内容
     */
    @Query(value = "SELECT resume_content from resume_template where resume_id = ? ",nativeQuery = true)
    String getResumeContentByResumeId(String resumeId);

    /**
     * 查询模板简历的名字和各自的ID
     * @return
     */
    @Query(value = "SELECT resume_name,resume_id from resume_template ",nativeQuery = true)
    List<Map<String,String>> getResumeTemplateTypeList();

}
