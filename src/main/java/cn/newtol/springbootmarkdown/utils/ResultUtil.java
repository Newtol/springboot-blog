package cn.newtol.springbootmarkdown.utils;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:58 2019/1/25
 */
import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.myEnum.ResultEnum;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 13:14 2018/11/10
 */
public class ResultUtil {
    /**
     * @Author: 公众号：Newtol
     * @Description: 请求成功，返回带参数结果
     * @Date: Created in 21:16
     * @param:
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setErrorCode(ResultEnum.SUCCESS.getErrorCode());
        resultVO.setErrorMsg(ResultEnum.SUCCESS.getErrorMsg());
        resultVO.setData(object);
        return resultVO;
    }
    /**
     * @Author: 公众号：Newtol
     * @Description: 请求成功，返回不带参数结果
     * @Date: Created in 21:16
     * @param:
     */
    public static ResultVO success(){
        return success(null);
    }

    /**
     * @Author: 公众号：Newtol
     * @Description:  访问错误，自定义返回参数
     * @Date: Created in 21:17
     * @param:
     */
    public static ResultVO error(Integer code ,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setErrorCode(code);
        resultVO.setErrorMsg(msg);
        return resultVO;
    }

    /**
     * @Author: 公众号：Newtol
     * @Description: 访问错误，使用预定义返回参数
     * @Date: Created in 21:17
     * @param:
     */
    public static  ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setErrorCode(resultEnum.getErrorCode());
        resultVO.setErrorMsg(resultEnum.getErrorMsg());
        return resultVO;
    }
}
