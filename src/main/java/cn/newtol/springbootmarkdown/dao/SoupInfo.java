package cn.newtol.springbootmarkdown.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 公众号：Newtol
 * @Description: 毒鸡汤实体
 * @Date: Created in 14:46 2019/6/19
 * @params:
 */

@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class SoupInfo extends BaseDO{
    /**
     * 毒鸡汤ID
     */
    @Column
    private String contentId;

    /**
     * 毒鸡汤内容
     */
    @Column
    @NotEmpty(message = "毒鸡汤内容不能为空")
    private String content;
}
