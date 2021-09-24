package com.shengrong.chemicalsystem.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengrong.chemicalsystem.model.entity.PersonEntity;
import com.shengrong.chemicalsystem.model.entity.VersionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xueke274
 */
@Mapper
public interface VersionDao extends BaseMapper<VersionEntity> {
    
}

