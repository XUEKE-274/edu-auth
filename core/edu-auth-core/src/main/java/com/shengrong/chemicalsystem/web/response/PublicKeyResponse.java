package com.shengrong.chemicalsystem.web.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PublicKeyResponse {
    private String exponent;
    private String modulus;
}
