//package com.example.basicproject.global.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

//@Slf4j
//public class LoginFilter implements Filter {
//
//    private static final String[] WHITE_LIST = {"/", "/api/members", "/api/members/login"};
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String uri = httpRequest.getRequestURI();
//        HttpSession session = httpRequest.getSession(false);
//
//        log.info("로그인 필터 실행. URI={}", uri);
//
//        // 화이트리스트에 없는 URI만 로그인 검사
//        if (!isWhiteList(uri)) {
//            if (session == null || session.getAttribute("memberId") == null) {
//                throw new RuntimeException("로그인 해주세요!");
//            }
//        }
//
//        // 다음 필터 or 서블릿(컨트롤러)으로 넘어감
//        chain.doFilter(request, response);
//    }
//
//    private boolean isWhiteList(String uri) {
//        return PatternMatchUtils.simpleMatch(WHITE_LIST, uri);
//    }
//}
