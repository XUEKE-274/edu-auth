package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengrong.chemicalsystem.model.entity.PersonEntity;
import com.shengrong.chemicalsystem.model.entity.TotalEntity;
import com.shengrong.chemicalsystem.service.PersonService;
import com.shengrong.chemicalsystem.service.TotalService;
import org.springframework.stereotype.Service;

/**
 * @author xueke274
 */
@Service
public class PersonServiceImpl extends AbstractBaseService<PersonEntity> implements PersonService {


    public PersonServiceImpl(BaseMapper<PersonEntity> dao) {
        super(dao);
    }
}
