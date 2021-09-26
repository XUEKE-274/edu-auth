package com.shengrong.chemicalsystem.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author xueke274
 */
@Getter
@Setter
@ToString
public class VersionResponse {

    private List<VersionDetail> versionDetails;


    @Getter
    @Setter
    @ToString
    public static class VersionDetail {
        private String id;
        private String version;
        private List<String> names;
    }



}
