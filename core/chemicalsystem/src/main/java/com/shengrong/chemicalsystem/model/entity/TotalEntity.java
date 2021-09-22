package com.shengrong.chemicalsystem.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@TableName("total_entity")
public class TotalEntity extends BaseEntity {

    //订单名称
    @TableField("creator")
    private String creator;

    //订单编号
    @TableField("version")
    private String version;

    //订单类型
    @TableField("result")
    private String result;

}
