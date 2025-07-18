package com.lms.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.*;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebSecurityConfig {

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            // Correct way to configure CORS in Spring Security 6+
	            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Link to your CorsConfigurationSource bean
	            // OR if you want to use the default configuration defined by a CorsFilter bean or WebMvcConfigurer:
	            // .cors(Customizer.withDefaults())

	            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (configure properly for production!)
	            .headers(headers -> headers.frameOptions().disable()) // Allow H2 Console to load in a frame
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 Console
	                .anyRequest().permitAll() // Allow all other requests for now (adjust as needed)
	                // If you want all other requests to be authenticated:
	                // .anyRequest().authenticated()
	            );

	        return http.build();
	    }

	  @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Your frontend origin
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")); // Include OPTIONS
	        configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
	        configuration.setAllowCredentials(true);
	        configuration.setMaxAge(3600L); // How long the preflight response can be cached
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration); // Apply to all paths
	        return source;
	    }
}
