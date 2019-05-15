package cn.newtol.springbootmarkdown.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

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

}
