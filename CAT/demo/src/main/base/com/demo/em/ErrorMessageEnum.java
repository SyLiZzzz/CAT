package com.demo.em;

public enum ErrorMessageEnum {
    SYS_ERROR(1001, "系统异常，请联系管理员!"),
    NETWORK(1002,"网络连接失败，请联系管理员!"),
    FORMAT_ERROR(1003,"账号格式不正确，或者密码错误!"),
    LOGIN_ERROR(1004,"账号不存在，或者密码不正确!"),
    ADD_USER_ERROR(1005,"新增用户失败！"),
    LOGIN_TIMEOUT(1006,"登录超时,请登录后在尝试"),
    REPEAT_ACCOUNT(1007,"账号已存在"),
    UPDATE_USER(1008,"修改用户失败"),
    UPDATE_USER_PHONE(1009,"手机号码格式不正确"),
    UPDATE_USER_PASSWORD(1010,"密码长度至少为6位"),
    DELETED_USER(1011,"删除失败"),
    UPDATE_USER_FAILE(1012,"该账户已被删除"),
    ADD_LOGINLOG_ERROR(1013,"新增登录日志失败"),
    REGISTER_USER(1014,"用户注册失败"),
    SELECT_DRIKN_BY_ID(1015,"饮品信息查询失败")
    ;
    private int code;
    private String mesg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    ErrorMessageEnum(int code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
