package cn.newtol.springbootblog.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: 公众号：Newtol
 * @Description: 时间轴实体
 * @Date: Created in 14:12 2019/6/20
 * @params:
 */

@EqualsAndHashCode(callSuper = true)
@Component
@Data
@Entity
@Table
public class TimelineInfo extends BaseDO {
    /**
     * 时间轴内容
     */
    private String content;

    /**
     * 时间轴标题
     */
    private String title;
}
