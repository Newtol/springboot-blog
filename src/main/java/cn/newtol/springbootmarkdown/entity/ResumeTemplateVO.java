package cn.newtol.springbootmarkdown.entity;
import lombok.Data;
import org.springframework.stereotype.Component;



/**
 * @Author: 公众号：Newtol
 * @Description: 简历编辑页面下拉菜单使用
 * @Date: Created in 17:49 2019/5/5
 * @params:
 */
@Component
@Data
public class ResumeTemplateVO {

    /**
     * 简历类型
     */
    private String resume_type;
    /**
     * 简历Id
     */
    private String resume_id;

}
