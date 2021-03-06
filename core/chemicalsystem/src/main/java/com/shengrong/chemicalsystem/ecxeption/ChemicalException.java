package com.shengrong.chemicalsystem.ecxeption;

import lombok.Getter;

@Getter
public class ChemicalException extends RuntimeException {

    private ExceptionCodeEnum exceptionCodeEnum;

    public ChemicalException(){
        this.exceptionCodeEnum = ExceptionCodeEnum.CS000;
    }

    public ChemicalException(ExceptionCodeEnum exceptionCodeEnum){
        this.exceptionCodeEnum = exceptionCodeEnum;
    }


}
