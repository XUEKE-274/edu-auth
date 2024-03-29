package com.shengrong.chemicalsystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.ecxeption.CoreException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
public class TokenUtils {

    private static final String USER_ID = "userId";

    private static final String SECRET = "c3MiOiJyb290IiwiZXhwIjoxNTU3OTI2OTY3";

    private static final String ISS = "chemicalsystemApplicationAdmin";

    public static String getToken(HttpServletRequest request) {
        return request.getHeader(CommonConstant.TOKEN);
    }

    public static String createToken (String userId) throws CoreException {
        try {
            Date expiresTime = new Date(System.currentTimeMillis() + CommonConstant.DEF_EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer(ISS)
                    .withExpiresAt(expiresTime)
                    .withClaim(USER_ID, userId)
                    .sign(algorithm);
        } catch (Exception e){
            log.error("token加密失败", e);
            throw new CoreException(ExceptionCodeEnum.TOKEN_ENCRYPTION);
        }

    }

    public static String getIdByToken(String token) throws CoreException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISS)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(USER_ID).asString();
        } catch (Exception e){
            log.error("token校验失败", e);
            throw new CoreException(ExceptionCodeEnum.AUTHENTICATION);
        }
    }



}
