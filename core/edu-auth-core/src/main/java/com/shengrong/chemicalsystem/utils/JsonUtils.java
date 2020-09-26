package com.shengrong.chemicalsystem.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

    public static String toString (Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }

    public static <T> T parse(String text, Class<T> tClass){
        return JSON.parseObject(text, tClass);
    }
}
