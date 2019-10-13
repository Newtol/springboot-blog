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
 * @Description: 简历实体
 * @Date: Created in 22:50 2019/4/29
 * @params:
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class ResumeInfo extends BaseDO {

    /**
     * 简历名
     */
    @Column(nullable = false)
    @NotEmpty
    private String title;

    /**
     * 简历内容摘要
     */
    @Column(length = 150,nullable = false)
    @NotEmpty
    private String summary;

    /**
     * 用户ip地址（辨别匿名用户使用）
     */
    @Column(length = 32,nullable = false)
    private String ip;

    /**
     * 注册用户Id,非注册则为空
     */
    @Column
    private String userId;

    /**
     * 简历类型
     */
    @Column
    private Integer type;

    /**
     * 简历存储URL
     */
    private String resumeUrl;
}
