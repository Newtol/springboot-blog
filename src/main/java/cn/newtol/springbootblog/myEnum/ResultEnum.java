package cn.newtol.springbootblog.myEnum;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 16:31 2019/1/25
 */


public enum ResultEnum {
    /**
     * 系统未知错误
     */
    UNKONW_ERROR(-1,"未知错误："),
    /**
     * 请求成功
     */
    SUCCESS(0,"success"),
    /**
     * 请求缺少参数
     */
    LACK_PARAMETER(1,"缺少参数"),
    /**
     * 账号或者密码错误
     */
    PASSWOED_ERROR(2,"账号或密码错误"),
    /**
     * 账号存在
     */
    ACCOUNT_EXSIT(3,"账号已经存在"),

    /**
     * 验证码错误
     */
    ValidCode_ERROR(4,"验证码错误"),

    /**
     * 验证码过期
     */
    ValidCode_EXPIRED(5,"验证码过期"),

    /**
     * 验证码为空
     */
    ValidCcde_EMPTY(6,"验证码为空"),

    /**
     * 获取手机或邮箱验证码超过次数
     */
     ValidCodeCountNum_OverFlow(7,"超过获取次数"),

    /**
     * 转换文件错误
     */
    Transfer_ERROR(9,"文件转换错误"),

    /**
     * 重置密码错误
     */
    ResetPassWord_ERROR(8,"重置密码错误"),

    /**
     * 文件不存在
     */
    Content_NO_EXISTS(10,"文件不存在"),

    /**
     * 已经点过赞
     */
    Praise_EXISTS(11,"已经点过赞拉"),

    /**
     * 点赞失败
     */
    Praise_ERROR(12,"点赞走丢了，请稍后再试");




    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误提示信息
     */
    private String errorMsg;

    ResultEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

