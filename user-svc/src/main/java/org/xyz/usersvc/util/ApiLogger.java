package org.xyz.usersvc.util;

import feign.Logger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Component
public class ApiLogger extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var requestWrapper = new ContentCachingRequestWrapper(request);
        var responseWrapper = new ContentCachingResponseWrapper(response);

        var startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            var duration = System.currentTimeMillis() - startTime;
//            logRequest(requestWrapper);
//            logResponse(responseWrapper);
//            responseWrapper.copyBodyToResponse();
        }
    }



    public static void logRequest(ContentCachingRequestWrapper request){
        byte[] content = request.getContentAsByteArray();
        String body = null;
        if (content.length > 0) {
            body = new String(content, StandardCharsets.UTF_8);
        }
        log.info("Incoming Request [Method: {}] [URI: {}] [Headers: {}] [Body: {}]",
                request.getMethod(), request.getRequestURL() + request.getRequestURI(), getHeaders(request), body);
    }

    public static void logResponse(ContentCachingResponseWrapper response){
        byte[] content = response.getContentAsByteArray();
        String body = null;
        if (content.length > 0) {
            body = new String(content, StandardCharsets.UTF_8);
        }

        log.info("Outgoing Response [Headers: {}] [Body: {}]",
                getHeaders(response), body);
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            var headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }

    public static Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();

        for (String headerName: headerNames) {
            headers.put(headerName, response.getHeader(headerName));
        }
        return headers;
    }
}
