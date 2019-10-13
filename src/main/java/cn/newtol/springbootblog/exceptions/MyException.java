package cn.newtol.springbootblog.exceptions;


import cn.newtol.springbootblog.myEnum.ResultEnum;
import lombok.Data;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 13:35 2018/11/10
 */
@Data
public class MyException extends RuntimeException {
    private Integer errorCode;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getErrorMsg());
        this.errorCode = resultEnum.getErrorCode();
    }

}
