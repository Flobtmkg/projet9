package com.projet9.microserviceaventures;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SecurityFilter extends GenericFilterBean
{
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;
        String path = httpRequest.getRequestURI();
        //extract token from header
        final String accessToken = httpRequest.getHeader("ZuulOrigin");
        if (null != accessToken || path.contains("/actuator")) {
            chain.doFilter(request, response);
        }
    }
}
