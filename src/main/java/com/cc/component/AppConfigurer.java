package com.cc.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(LoginInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public LoginInterceptor LoginInterceptor() {
		return new LoginInterceptor();
	}

}
