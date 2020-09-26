package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.model.dto.UserContextDTO;
import com.shengrong.chemicalsystem.service.LoginContextService;
import org.springframework.stereotype.Service;

@Service
public class LoginContextServiceImpl implements LoginContextService {

    private ThreadLocal<UserContextDTO> threadLocal = new ThreadLocal<>();

    @Override
    public void setContext(String id, String username) {
        UserContextDTO dto = new UserContextDTO();
        dto.setId(id);
        dto.setUsername(username);
        threadLocal.set(dto);
    }

    @Override
    public UserContextDTO getContext() {
        return threadLocal.get();
    }
}
