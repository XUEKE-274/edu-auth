package com.shengrong.chemicalsystem.ecxeption;

import lombok.Getter;

@Getter
public class CoreException extends Exception {

    private ExceptionCodeEnum exceptionCodeEnum;

    public CoreException(){
        this.exceptionCodeEnum = ExceptionCodeEnum.CORE;
    }

    public CoreException(ExceptionCodeEnum exceptionCodeEnum){
        this.exceptionCodeEnum = exceptionCodeEnum;
    }


}
