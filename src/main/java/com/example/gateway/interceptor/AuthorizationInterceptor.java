package com.example.gateway.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthorizationInterceptor implements AsyncHandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    verifyToken(token);

//    Long userId = auth.parseUserIdFromToken(token);
//    log.info("[Request User ID] " + userId);
//    User user = findUserById(userId)
//        .orElseThrow(() -> new UserNotFoundException("[INTERCEPTOR Exception] user not found"));
//
//    checkUserStatus(user.getStatus());
    return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
  }

  private void verifyToken(String token) {

  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
}