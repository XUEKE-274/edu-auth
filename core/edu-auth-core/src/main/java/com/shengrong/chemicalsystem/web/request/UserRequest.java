package com.shengrong.chemicalsystem.web.request;

import com.shengrong.chemicalsystem.web.request.common.PageRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest extends PageRequest {

    private String username;

}
