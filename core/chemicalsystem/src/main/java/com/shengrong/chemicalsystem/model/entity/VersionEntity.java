package com.shengrong.chemicalsystem.model.entity;

import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xueke274
 */
@Getter
@Setter
@ToString
public class VersionEntity extends BaseEntity {
    private String name;
}
