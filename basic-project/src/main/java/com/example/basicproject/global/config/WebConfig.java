//package com.example.basicproject.global.config;

//import com.example.basicproject.global.common.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class WebConfig {
//    @Bean
//    public FilterRegistrationBean<jakarta.servlet.Filter> loginFilter() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoginFilter());
//        registrationBean.setOrder(2); // 실행 순서
//        registrationBean.addUrlPatterns("/*"); // 모든 요청에 적용
//        return registrationBean;
//    }
//}
