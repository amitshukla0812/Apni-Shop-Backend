package com.apnishop.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // Set FRONTEND_URL as an environment variable in production
    // (e.g. https://your-app.vercel.app). Local dev (localhost:5173)
    // is always allowed alongside it.
    @Value("${FRONTEND_URL:}")
    private String frontendUrl;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                var mapping = registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*");

                if (frontendUrl != null && !frontendUrl.isBlank()) {
                    mapping.allowedOrigins("http://localhost:5173", frontendUrl);
                } else {
                    mapping.allowedOrigins("http://localhost:5173");
                }
            }
        };
    }
}
