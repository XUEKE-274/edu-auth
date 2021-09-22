package com.shengrong.chemicalsystem.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengrong.chemicalsystem.model.entity.OrderEntity;
import com.shengrong.chemicalsystem.model.entity.TotalEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TotalDao extends BaseMapper<TotalEntity> {

    void insert0(TotalEntity entity);
}
