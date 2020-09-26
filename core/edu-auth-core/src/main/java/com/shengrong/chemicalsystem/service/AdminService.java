package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.model.entity.AdminEntity;


public interface AdminService{
    AdminEntity getByName(String username);

    AdminEntity getById(String id);
}
