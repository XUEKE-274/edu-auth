package com.shengrong.chemicalsystem.utils;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.ecxeption.CoreException;
import com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.web.response.common.CommonResponse;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static CommonResponse getDefResponse(){
        CommonResponse dto = new CommonResponse();
        dto.setCode(CommonConstant.SUCCESS);
        dto.setDesc(CommonConstant.SUCCESS);
        dto.setFlowId(MDC.get(CommonConstant.FLOW_ID));
        return dto;
    }

    public static CommonResponse getDataResponse(Object data){
        CommonResponse response = getDefResponse();
        response.setResult(data);
        return response;
    }


    public static CommonResponse dealCode(ExceptionCodeEnum codeEnum){
        CommonResponse response = new CommonResponse();
        response.setCode(codeEnum.getCode());
        response.setDesc(codeEnum.getDesc());
        response.setFlowId(MDC.get(CommonConstant.FLOW_ID));
        return response;
    }


    public static CommonResponse dealException(CoreException e){
        ExceptionCodeEnum codeEnum = e.getExceptionCodeEnum();
        return dealCode(codeEnum);
    }

    public static void print(HttpServletResponse response, Object text) throws IOException {
        response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
        response.getWriter().println(JsonUtils.toString(text));
    }
}
