package com.shengrong.chemicalsystem.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;

@Configuration
public class FilterConfig {


    @Resource
    private Filter flowIdFilter;

    @Resource
    private Filter authenticationFilter;

    @Resource
    private Filter tokenFilter;


    @Bean
    public FilterRegistrationBean<Filter> flowId() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(flowIdFilter);
        registration.addUrlPatterns("/*");
        registration.setName("fLowIdFilter");
        registration.setOrder(100);
        return registration;
    }


    @Bean
    public FilterRegistrationBean<Filter> authentication() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(authenticationFilter);
        registration.addUrlPatterns("/*");
        registration.setName("authenticationFilter");
        registration.setOrder(101);
        return registration;
    }


    @Bean
    public FilterRegistrationBean<Filter> token() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(tokenFilter);
        registration.addUrlPatterns("/*");
        registration.setName("tokenFilter");
        registration.setOrder(102);
        return registration;
    }

}
