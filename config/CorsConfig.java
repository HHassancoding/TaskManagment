package com.example.TaskManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOriginPatterns("https://*.lovable.app", "https://*.lovableproject.com", "https://*.netlify.app")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
