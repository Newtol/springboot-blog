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
 * @Description: 赞实体
 * @Date: Created in 17:09 2019/6/13
 * @params:
 */
@Component
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class ContentPraise extends BaseDO {
    /**
     * 文章ID
     */
    @NotEmpty(message = "文章ID不能为空")
    @Column
    private String contentId;

    /**
     * 点赞用户的IP地址
     */
    @Column
    private String ip;
}
