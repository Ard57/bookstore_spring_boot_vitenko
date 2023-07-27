package com.vitenko.bookstore;

import com.vitenko.bookstore.web.filter.AuthorizationFilter;
import com.vitenko.bookstore.web.filter.ManagerAccessFilter;
import com.vitenko.bookstore.web.interceptor.LoggingInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/user/*", "/order/*", "/logout", "/cart/purchase");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ManagerAccessFilter> managerAccessFilter() {
        FilterRegistrationBean<ManagerAccessFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ManagerAccessFilter());
        registrationBean.addUrlPatterns("/user/*", "/order/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}