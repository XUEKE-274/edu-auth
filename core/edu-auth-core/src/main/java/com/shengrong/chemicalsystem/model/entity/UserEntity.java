package com.shengrong.chemicalsystem.model.entity;

import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@ToString
@Slf4j

public class UserEntity extends BaseEntity {


    private String username;


    private String password;

}
