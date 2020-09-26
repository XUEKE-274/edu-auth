package com.shengrong.chemicalsystem.dao;

import com.shengrong.chemicalsystem.model.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDao {
    AdminEntity getById(@Param("id") String id);
    AdminEntity getByName(@Param("username") String username);
}
