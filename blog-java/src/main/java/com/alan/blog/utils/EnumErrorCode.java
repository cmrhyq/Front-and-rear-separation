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
    ACC_NO_REG("000001", "账号未注册", 1),
    /**
     * 账号已经注册 - account has been register
     */
    ACC_HAS_BEEN_REG("000002","注册失败，账号已注册",2),
    /**
     * 账号密码错误 - user account password error
     */
    ACC_PWD_ERR("000003", "账号密码错误", 3),
    /**
     * 账号冻结 -user account freeze
     */
    ACC_FREEZE("000004", "账号冻结", 4),
    /**
     * token认证失败，未通过拦截器
     */
    AUTHEN_FAIL("000005", "认证失败,请先登录", 5),
    /**
     * 帐号状态异常 - user account status abnormal
     */
    ACC_STATUS_ABNORMAL("000006", "帐号状态异常", 6),
    /**
     * 账号注册失败 - account register fail
     */
    ACC_REG_FAIL("000007","注册失败",7),

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
