package com.shengrong.chemicalsystem.model.entity.commom;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class BaseEntity{


    @TableId("id")
    private String id;


    @TableField("create_time")
    private Timestamp createTime;


    @TableField("modify_time")
    private Timestamp modifyTime;

}
