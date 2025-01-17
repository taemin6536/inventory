package com.v1.global.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(AbstractHttpConfigurer::disable)  // 필요에 따라 CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()  // 인증 없이 접근 가능
                        .anyRequest().authenticated()  // 나머지는 인증 필요
                )
                .formLogin(withDefaults());  // 폼 로그인 활성화 (필요시 비활성화)

        return http.build();
    }
}
