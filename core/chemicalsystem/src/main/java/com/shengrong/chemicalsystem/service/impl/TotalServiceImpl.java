package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengrong.chemicalsystem.dao.OrderDao;
import com.shengrong.chemicalsystem.model.entity.OrderEntity;
import com.shengrong.chemicalsystem.model.entity.TotalEntity;
import com.shengrong.chemicalsystem.service.OrderService;
import com.shengrong.chemicalsystem.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xueke274
 */
@Service
public class TotalServiceImpl extends AbstractBaseService<TotalEntity> implements TotalService {


    public TotalServiceImpl(BaseMapper<TotalEntity> dao) {
        super(dao);
    }
}
