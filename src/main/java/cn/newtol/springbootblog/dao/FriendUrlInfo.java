package cn.newtol.springbootblog.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: 公众号：Newtol
 * @Description: 友链实体
 * @Date: Created in 22:22 2019/6/20
 * @params:
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class FriendUrlInfo extends BaseDO{
    /**
     * 博客名
     */
    private String name;

    /**
     * 友链链接
     */
    private String url;
}
