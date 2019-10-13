package cn.newtol.springbootblog.handle;


import cn.newtol.springbootblog.entity.ResultVO;
import cn.newtol.springbootblog.exceptions.MyException;
import cn.newtol.springbootblog.myEnum.ResultEnum;
import cn.newtol.springbootblog.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 13:55 2018/11/10
 */

@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    /**
    * @Author: 公众号：Newtol
    * @Description: 拦截一些自定义的警告
    * @Date: Created in 0:07
    * @param:
    */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ResultVO testExceptionHandler(MyException e){
        return ResultUtil.error(e.getErrorCode(),e.getMessage());
    }

    /**
    * @Author: 公众号：Newtol
    * @Description: 拦截参数验证错误
    * @Date: Created in 0:07
    * @param:
    */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVO validExceptionHandler(BindException e){
        String  msg  = e.getBindingResult().getFieldError().getDefaultMessage();
        logger.info("参数验证错误:"+msg);
        return ResultUtil.error(ResultEnum.LACK_PARAMETER.getErrorCode(),msg);
    }

    /**
    * @Author: 公众号：Newtol
    * @Description: 系统未知错误拦截
    * @Date: Created in 0:15
    * @param:
    */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResultVO systemExceptionHandler(Exception e){
//        logger.error("系统未知错误:"+e.getMessage());
//        return ResultUtil.error(ResultEnum.UNKONW_ERROR);
//    }

//    @ExceptionHandler(NullPointerException.class)
//    @ResponseBody
//    public ResultVO  NullPointerExceptionHandler(NullPointerException e){
//        String  msg  = e.getMessage();
//        logger.info("参数验证错误:"+msg);
//        return ResultUtil.error(456,msg);
//    }
}

