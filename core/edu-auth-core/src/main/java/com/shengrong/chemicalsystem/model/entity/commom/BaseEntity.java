package com.shengrong.chemicalsystem.model.entity.commom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class BaseEntity{

    //主键
    private String id;

    //创建时间
    private Timestamp createTime;

    //修改时间
    private Timestamp modifyTime;

    // 创建人
    private String createBy;

    //修改人
    private String modifyBy;

}
