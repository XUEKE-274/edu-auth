package com.shengrong.chemicalsystem.model.entity;

import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminEntity extends BaseEntity {


    private String username;


    private String password;
}
