package cn.newtol.springbootblog.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 15:33 2019/1/25
 */

@Component
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     *  错误提示信息
     */
    private String errorMsg;

    /**
     * 数据块
     */
    private T data;
}
