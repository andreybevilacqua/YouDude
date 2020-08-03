package com.abevilacqua.youdude.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetTime;

@Slf4j
@Component
public class RequestFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest httpRequest,
                                  HttpServletResponse httpResponse,
                                  FilterChain filterChain) throws ServletException, IOException {
    log.info("Filtered request to URL {} on time {}", httpRequest.getRequestURL(), OffsetTime.now());
    filterChain.doFilter(httpRequest, httpResponse);
    log.info("Filtered resolved to URL {} on time {}", httpRequest.getRequestURL(), OffsetTime.now());
  }
}
