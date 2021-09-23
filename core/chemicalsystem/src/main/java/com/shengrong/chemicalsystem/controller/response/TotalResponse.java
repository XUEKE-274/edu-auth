package com.shengrong.chemicalsystem.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xueke274
 */
@Getter
@Setter
@ToString
public class TotalResponse {


    private List<String> mvpArr = new ArrayList<>();
    private List<TotalDetail> totals = new ArrayList<>();


    @Getter
    @Setter
    @ToString
    public static class TotalDetail {
        private String mvp;
        private String name;
        private BigDecimal result;
        private int num;
    }

}
