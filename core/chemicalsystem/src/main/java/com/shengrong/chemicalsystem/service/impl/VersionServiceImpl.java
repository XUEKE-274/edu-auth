package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengrong.chemicalsystem.model.entity.PersonEntity;
import com.shengrong.chemicalsystem.model.entity.VersionEntity;
import com.shengrong.chemicalsystem.service.PersonService;
import com.shengrong.chemicalsystem.service.VersionService;
import org.springframework.stereotype.Service;

/**
 * @author xueke274
 */
@Service
public class VersionServiceImpl extends AbstractBaseService<VersionEntity> implements VersionService {


    public VersionServiceImpl(BaseMapper<VersionEntity> dao) {
        super(dao);
    }
}
