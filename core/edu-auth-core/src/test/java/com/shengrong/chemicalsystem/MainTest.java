package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.utils.HashUtils;
import com.shengrong.chemicalsystem.utils.IdUtils;

public class MainTest {
    public static void main(String[] args) {
        System.out.println(IdUtils.getUUID());
        System.out.println(HashUtils.doHash("admin"));
    }
}
