package com.dealmacha;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import groovy.lang.Singleton;

@Component
@Singleton
public class ResponseCorsFilter implements Filter {

    private void addHeadersFor200Response(final HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers",
                "Cache-Control, Pragma, Origin, Authorization, Content-Type,Accept, X-Requested-With");
        response.addHeader("Access-Control-Allow-Methods", "GET, PUT, OPTIONS,POST, X-XSRF-TOKEN, DELETE");

    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse alteredResponse = ((HttpServletResponse) servletResponse);
            addHeadersFor200Response(alteredResponse);
        }
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (!path.startsWith("/**")) {

            httpResponse.addHeader("Access-Control-Allow-Credentials", "true");

            if (httpRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpRequest.getMethod())) {
                httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                /*  httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                httpResponse.addHeader("Access-Control-Max-Age", "1800");*/
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }
}
