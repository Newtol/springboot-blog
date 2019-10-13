package cn.newtol.springbootblog.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 21:35 2019/6/16
 * @params:
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class GuestBookInfo extends BaseDO{

    /**
     * 用户昵称
     */
    @Column
    @NotEmpty(message = "昵称不能为空")
    private String username;

    /**
     * 用户邮箱、必要时用于消息回复
     */
    @Column
    @NotEmpty(message = "邮箱不能为空")
    private String userEmail;

    /**
     * 留言内容
     */
    @Column
    @NotEmpty(message = "留言不可为空")
    private String message;

    /**
     * 用于防止刷留言
     */
    @NotEmpty(message = "验证码不可为空")
    @Transient
    private String validCode;

    /**
     * 留言ID
     */
    @Column
    private String msgId;

    /**
     * 留言的父ID,用于站长回复时使用
     */
    @Column
    private String pId = "0";

}
