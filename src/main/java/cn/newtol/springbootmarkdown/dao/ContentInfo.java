package cn.newtol.springbootmarkdown.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 22:44 2019/5/15
 * @params:
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class ContentInfo extends BaseDO{
    /**
     * 文章标题
     */
    @Column(nullable = false)
    @NotEmpty(message = "标题不能为空")
    private String title;
    /**
     * 文章摘要
     */
    @Column(length = 150,nullable = false)
    private String summary;
    /**
     * 文章ID
     */
    @Column
    private String contentId;
    /**
     * 文章
     */
    @Column(nullable = false)
    @NotEmpty(message = "文章类型为空")
    private String contentType;
    /**
     * 作者
     */
    @NotEmpty(message = "作者不能为空")
    @Column(nullable = false)
    private String writer;
    /**
     * 阅读量
     */
    @Column
    private Integer readNum=0;
    /**
     * 关键词
     */
    @Column
    @NotEmpty(message = "关键词不能为空")
    private String stringKey;

    /**
     * 是否可见：1可见，0不可见
     */
    @Column
    @NotNull(message = "请选择是否可见")
    private Integer isView;

    /**
     * 是否为原创：1原创，0为非原创
     */
    @Column
    @NotNull(message = "请选择是否为原创")
    private Integer isOrigin;

    /**
     * 文章内容
     */
    @Transient
    @NotEmpty(message = "文章不能为空")
    private String content;

    /**
     * 文章链接
     */
    @Column
    private String contentUrl;

}
