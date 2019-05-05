package cn.newtol.springbootmarkdown.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 23:11 2019/4/29
 * @params:
 */
@Component
@Data
public class ResumeDTO {
    /**
     * 文件名
     */
    @NotEmpty(message = "标题不能为空")
    private String title;

    /**
     * 文件内容
     */
    @NotEmpty(message = "内容不能为空")
    private String  content;
}
