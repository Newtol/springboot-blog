package cn.newtol.springbootblog.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 公众号：Newtol
 * @Description: 简历模板实体
 * @Date: Created in 14:13 2019/5/3
 * @params:
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class ResumeTemplate extends BaseDO{
    /**
     * 简历名字
     */
    @Column
    @NotEmpty(message = "简历名字不能为空")
    private String resumeName;
    /**
     * 简历内容
     */
    @Column(columnDefinition ="TEXT" )
    @NotEmpty(message = "简历内容不能为空")
    private String resumeContent;

    /**
     * 简历类型
     */
    @Column
    @NotEmpty(message = "简历类型不能为空")
    private String resumeType;
    /**
     * 简历Id
     */
    @Column(unique = true)
    private String resumeId;
}
