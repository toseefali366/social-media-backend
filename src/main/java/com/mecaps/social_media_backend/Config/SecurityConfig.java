package com.mecaps.social_media_backend.Config;

import com.mecaps.social_media_backend.Security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private  final JwtAuthFilter jwtAuthFilter;
    private  final UserDetailsService userDetailsService;
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth-> auth.requestMatchers("/user/signup").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/logout").authenticated()

//                .requestMatchers("/otp/*").permitAll()
                .anyRequest().authenticated());

    http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

return http.build();
}

}
