package com.dhernandez.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
public class ApiCarApplication{

	public static void main(String[] args) {
		SpringApplication.run(ApiCarApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("/**").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    return http
	            .csrf().disable()
	            .authorizeHttpRequests()
	            .anyRequest().permitAll()
	            .and()
	            .build();
	}
	


}
