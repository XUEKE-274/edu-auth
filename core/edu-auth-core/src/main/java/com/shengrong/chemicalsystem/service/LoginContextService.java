package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.model.dto.UserContextDTO;

public interface LoginContextService {

    void setContext(String id, String username);
    UserContextDTO getContext();

}
