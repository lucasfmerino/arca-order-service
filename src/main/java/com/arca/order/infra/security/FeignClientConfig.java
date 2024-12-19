package com.arca.order.infra.security;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor()
    {
        return requestTemplate ->
        {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null)
            {
                String token = attributes.getRequest().getHeader("Authorization");
                if (token != null)
                {
                    requestTemplate.header("Authorization", token);
                }
            }
        };
    }
}