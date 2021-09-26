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
@TableName("version")
public class VersionEntity extends BaseEntity {
    @TableField("name")
    private String name;
}
