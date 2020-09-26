package com.shengrong.chemicalsystem.web.filters;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class FlowIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("access FlowIdFilter success  >>> ");
        MDC.put(CommonConstant.FLOW_ID, IdUtils.getUUID());
        filterChain.doFilter(request, response);
    }
}
