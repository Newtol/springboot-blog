package cn.newtol.springbootmarkdown.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Author: 公众号：Newtol
 * @Description: 评论实体
 * @Date: Created in 15:01 2019/6/15
 * @params:
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class MessageInfo extends BaseDO {

    /**
     * 评论所在文章ID
     */
    @Column
    @NotEmpty(message = "评论所在文章ID不可为空")
    private String contentId;

    /**
     * 一级评论ID
     */
    @Column
    private String pId = "0";

    /**
     * 评论人ID
     */
    @Column
    @NotEmpty(message = "评论人不可为空")
    @Email(message = "请输入邮箱作为评论")
    private String fromId;

    /**
     * 被评论ID
     */
    @Column
    @Email(message = "请输入邮箱作为评论")
    private String toId;

    /**
     * 评论内容
     */
    @Column
    @NotEmpty(message = "评论不可为空")
    @Size(max = 200,message = "评论内容不可超过200字符")
    private String message;
    /**
     * 评论ID(如果为一级评论，则评论ID为二级评论的pId)
     */
    @Column
    private String msgId;

    /**
     * 验证码
     */
    @Transient
    @NotEmpty(message = "验证码不能为空")
    private String validCode;

}
