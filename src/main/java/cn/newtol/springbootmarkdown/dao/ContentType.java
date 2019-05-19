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
 * @Description:
 * @Date: Created in 23:13 2019/5/15
 * @params:
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class ContentType extends BaseDO{
    /**
     * 文章分类
     */
    @Column()
    @NotEmpty(message = "文章类目名不能为空")
    private String name;

//    /**
//     * 该类型文章数量
//     */
//    @Column
//    private Integer num=0;
}
