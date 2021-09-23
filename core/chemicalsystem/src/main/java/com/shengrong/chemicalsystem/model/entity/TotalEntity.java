package com.shengrong.chemicalsystem.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("total_entity")
public class TotalEntity extends BaseEntity {

    @TableField("mvp")
    private String mvp;

    @TableField("creator")
    private String creator;


    @TableField("version")
    private String version;


    @TableField("result")
    private String result;

}
