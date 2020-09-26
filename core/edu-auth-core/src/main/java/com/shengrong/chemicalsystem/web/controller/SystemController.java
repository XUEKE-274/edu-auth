package com.shengrong.chemicalsystem.web.controller;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.utils.RSAUtils;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;

@RestController
public class SystemController {

    @RequestMapping(method = RequestMethod.GET, value = CommonConstant.SYSTEM_PUBLIC_KEY)
    public Object getPublicKey(){
        RSAPublicKey publicKey = RSAUtils.getPublicKey();
        byte[] keyEncoded = publicKey.getEncoded();
        String pub = Base64.encodeBase64String(keyEncoded);
        return ResponseUtils.getDataResponse(pub);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public Object test(){
        return "SUCCESS";
    }

}
