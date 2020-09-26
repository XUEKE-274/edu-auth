package com.shengrong.chemicalsystem.web.filters;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.ecxeption.CoreException;
import com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.model.entity.AdminEntity;
import com.shengrong.chemicalsystem.service.AdminService;
import com.shengrong.chemicalsystem.service.LoginContextService;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import com.shengrong.chemicalsystem.utils.TokenUtils;
import com.shengrong.chemicalsystem.web.response.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private AdminService adminService;

    @Resource
    private LoginContextService loginContextService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("access TokenFilter success  >>> ");
        if (request.getRequestURI().equals(CommonConstant.SYSTEM_PUBLIC_KEY)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = TokenUtils.getToken(request);
        AdminEntity entity;
        try {
            if (StringUtils.isEmpty(token)) {
                throw new CoreException(ExceptionCodeEnum.AUTHENTICATION);
            }
            String id = TokenUtils.getIdByToken(token);
            entity = adminService.getById(id);
            if (entity == null){
                throw new CoreException(ExceptionCodeEnum.USER_NOT_EXIST);
            }
        } catch (CoreException e) {
            log.error("TokenFilter CoreException", e);
            CommonResponse data = ResponseUtils.dealException(e);
            ResponseUtils.print(response, data);
            return;
        }
        loginContextService.setContext(entity.getId(), entity.getUsername());
        filterChain.doFilter(request, response);

    }
}
