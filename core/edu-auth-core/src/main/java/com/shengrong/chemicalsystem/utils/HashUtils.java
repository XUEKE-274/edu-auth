package com.shengrong.chemicalsystem.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class HashUtils {

    private static final String SALT = "27727027606c4c21b3125ceb0b96bb04";
    public static String doHash(String message){
        try {
            message = message + SALT;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(message.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            return Hex.encodeHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }


    public static void main(String[] args) throws Exception {
        System.out.println(doHash("123"));
    }
}
