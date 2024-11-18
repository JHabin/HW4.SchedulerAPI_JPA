package com.example.scheduler_jpa;

import com.example.scheduler_jpa.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<Filter> loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(1); // 우선순위 설정
        filterRegistrationBean.addUrlPatterns("/api/*"); // /api로 시작하는 경로에만 필터 적용

        return filterRegistrationBean;
    }
}
