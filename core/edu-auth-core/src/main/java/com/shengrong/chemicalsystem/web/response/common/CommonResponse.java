package com.shengrong.chemicalsystem.web.response.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonResponse {
    private String code;
    private String desc;
    private String flowId;
    private Object result;
}
