package com.apnishop.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Serves image files placed in an "uploads" folder next to the running
// application, at http://localhost:8000/uploads/**
//
// This is a SEPARATE path from /product, /brand, etc. on purpose: those are
// REST API routes (e.g. GET /product/{id} looks up a Product by id), so
// images can't be served directly under /product/... without colliding
// with that route.
//
// To use: create an "uploads" folder next to where you run the app (e.g.
// next to pom.xml), and put the image files inside preserving their
// original relative paths, e.g.:
//   uploads/product/p3.jpg
//   uploads/product/p62.jpg
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/");
    }
}
