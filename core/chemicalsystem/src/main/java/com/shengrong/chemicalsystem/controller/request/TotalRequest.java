package com.shengrong.chemicalsystem.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author xueke274
 */
@Getter
@Setter
@ToString
public class TotalRequest {
    private String creator;
    private String version;
    private List<SingleData> result;



    @Getter
    @Setter
    @ToString
    public static class SingleData {
        private String name;
        private BigDecimal grade;
    }
}
