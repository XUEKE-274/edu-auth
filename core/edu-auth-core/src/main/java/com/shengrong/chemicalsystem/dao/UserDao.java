package com.shengrong.chemicalsystem.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {



    List<String> query();



}
