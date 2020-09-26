package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.dao.AdminDao;
import com.shengrong.chemicalsystem.model.entity.AdminEntity;
import com.shengrong.chemicalsystem.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public AdminEntity getByName(String username) {
        return adminDao.getByName(username);
    }
    @Override
    public AdminEntity getById(String id) {
        return adminDao.getById(id);
    }
}
