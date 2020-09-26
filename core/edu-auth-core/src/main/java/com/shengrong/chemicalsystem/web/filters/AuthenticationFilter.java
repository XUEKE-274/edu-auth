package com.shengrong.chemicalsystem.web.filters;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.ecxeption.CoreException;
import com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.model.entity.AdminEntity;
import com.shengrong.chemicalsystem.service.AdminService;
import com.shengrong.chemicalsystem.utils.*;
import com.shengrong.chemicalsystem.web.response.common.CommonResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private AdminService adminService;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("access AuthenticationFilter success  >>> ");
        String uri = request.getRequestURI();

        if (!CommonConstant.LOGIN.equals(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        String body = read(request.getInputStream());

        if (isWeChat()){
            //weChat TODO  call weChar
        }else {
            //web
            LoginDTO dto = JsonUtils.parse(body, LoginDTO.class);
            LoginVO vo = null;
            try {
                vo = doWebLogin(dto.getUsername(), dto.getPassword());
                CommonResponse dataResponse = ResponseUtils.getDataResponse(vo);
                ResponseUtils.print(response, dataResponse);
            } catch (CoreException e) {
                log.error("login fail, body = {}", body, e);
                CommonResponse data = ResponseUtils.dealException(e);
                ResponseUtils.print(response, data);
            }
        }

    }

    private LoginVO doWebLogin(String username, String password) throws CoreException {
        //  解密  TODO
//        password = RSAUtils.decrypt(password);
        AdminEntity entity =  adminService.getByName(username);
        if (entity == null) {
            throw new CoreException(ExceptionCodeEnum.USERNAME_PASSWORD);
        }
        String dbPwd = entity.getPassword();
        String userPwd = HashUtils.doHash(password);
        if (dbPwd.equals(userPwd)) {
            LoginVO vo = new LoginVO();
            vo.setUsername(entity.getUsername());
            vo.setToken(TokenUtils.createToken(entity.getId()));
            return vo;
        }
        throw new CoreException(ExceptionCodeEnum.USERNAME_PASSWORD);

    }

    private String read(ServletInputStream inputStream) throws IOException{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));){
            StringBuilder result = new StringBuilder();
            String temp = "";
            while ((temp = reader.readLine()) != null){
                result.append(temp);
            }
            return result.toString();
        }

    }

    private boolean isWeChat() {
        return false;
    }


    @Setter
    @Getter
    @ToString
    private static class LoginDTO{
        private String username;
        private String password;
    }

    @Setter
    @Getter
    @ToString
    private static class LoginVO{
        private String username;
        private String token;
        private Set<String> roles;
    }


}
