package com.shengrong.chemicalsystem.controller.request;

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
public class RelatePersonRequest {

    private String versionId;
    private List<String> names;


}
