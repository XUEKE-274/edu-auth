package com.shengrong.chemicalsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xueke274
 * @version 1.0
 * @date 2022/7/29 14:19
 * @description
 */
@Mapper
public interface TestDao {
    void insertTestTable(@Param("id") String id, @Param("name") String name);
    List<String> query(TestQuery query);
}
