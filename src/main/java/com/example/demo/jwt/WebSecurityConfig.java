package com.example.demo.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class WebSecurityConfig {

	private final UserDetailsService myUserDetailsService;
	private final JwtRequestFilter jwtRequestFilter;

	public WebSecurityConfig(UserDetailsService myUserDetailsService, JwtRequestFilter jwtRequestFilter) {
		this.myUserDetailsService = myUserDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/auth/register", "/auth/login",
	                             "/swagger-ui.html/**", "/swagger-ui/**", "/v3/api-docs/**")
	                .permitAll()
	            .anyRequest()
	                .authenticated()
	        )
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .authenticationProvider(authenticationProvider())
	        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling(handling -> handling
	            .authenticationEntryPoint((request, response, authException) -> {
	                response.setContentType("application/json");
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.getWriter().write("{\"error\": \"Please enter a valid token in Authorization header\"}");
	            })
	        );

	    return http.build();
	}

	        		
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
