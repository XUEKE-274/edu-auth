package com.shengrong.chemicalsystem.ecxeption;

import lombok.Getter;

@Getter
public enum  ExceptionCodeEnum {

    CORE("CORE", "核心异常"),
    PARAM("PARAM", "参数错误"),
    USERNAME_PASSWORD("USERNAME_PASSWORD", "账号或者密码错误"),
    TOKEN_ENCRYPTION("TOKEN_ENCRYPTION", "token加密失败"),
    AUTHENTICATION("AUTHENTICATION", "认证失败"),
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在"),
    ;

    private String code;
    private String desc;

    ExceptionCodeEnum (String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
