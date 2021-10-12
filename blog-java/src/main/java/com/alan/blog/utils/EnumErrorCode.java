package com.alan.blog.utils;

/**
 * <p>错误代码枚举类</p>
 *
 * @author Alan Huang
 * @version v1
 * @Title ErrorMessages
 * @date 2021/7/22-16:33
 * @Email cmrhyq@163.com
 */
public enum EnumErrorCode implements ErrorMessages {

    /**
     * 账号未注册 - user account no reg
     */
    ACC_NO_REG("00001", "账号未注册", 1),
    /**
     * 账号密码错误 - user account password error
     */
    ACC_PWD_ERR("000002", "账号密码错误", 2),
    /**
     * 账号冻结 -user account freeze
     */
    ACC_FREEZE("000003", "账号冻结", 3),
    /**
     * 帐号状态异常 - user account status abnormal
     */
    ACC_STATUS_ABNORMAL("0000004", "帐号状态异常", 4),
    /**
     * token认证失败，未通过拦截器
     */
    AUTHEN_FAIL("000005", "认证失败,请先登录", 5),
    /**
     * 注册失败，user account register fail
     */
    REG_FAIL("000006","注册失败",6),

    /**
     * 请求成功，success
     */
    SUCCESS("200000", "SUCCESS", 200),
    /**
     * 登录成功,user account login success
     */
    LOGIN_SUCCESS("200001", "登录成功", 201),
    /**
     * 注册成功，user account register success
     */
    REG_SUCCESS("200002", "注册成功", 202);


    /**
     * JSON返回的错误代码
     *
     * @since 1.0.0
     */
    private String infoCode;
    /**
     * JSON返回的错误信息
     *
     * @since 1.0.0
     */
    private String msg;
    /**
     * JSON返回的状态
     *
     * @since 1.0.0
     */
    private int status;


    private EnumErrorCode() {
    }

    private EnumErrorCode(String infoCode, String msg, int status) {
        this.infoCode = infoCode;
        this.msg = msg;
        this.status = status;
    }


    @Override
    public String getCode() {
        return this.infoCode;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public int getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + infoCode +
                ", msg='" + msg + '\'' +
                '}';
    }


}
