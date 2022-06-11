package org.winterframework.dashboard.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.winterframework.dashboard.security.core.JwtAccessDeniedHandler;
import org.winterframework.dashboard.security.core.JwtAuthenticationEntryPoint;
import org.winterframework.dashboard.security.core.JwtAuthenticationFilter;

import java.util.List;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // ALTHOUGH THIS SEEMS LIKE USELESS CODE,
        // IT'S REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
        return null;
    }

    @Profile("dev")
    @Bean
    public PermitAllRequestMatcher apiDocConfigurer() {
        return new ApiDocRequestMatcher();
    }


    @Bean
    public PermitAllRequestMatcher dynamicWhiteListRequestMatcher() {
        return new DynamicWhiteListRequestMatcher();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationEntryPoint authenticationErrorHandler,
                                           JwtAccessDeniedHandler accessDeniedHandler,
                                           JwtAuthenticationFilter jwtAuthenticationFilter,
                                           List<PermitAllRequestMatcher> requestMatchers) throws Exception {
        http.csrf().disable()
            .cors().disable()
            // .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().authenticationEntryPoint(authenticationErrorHandler)
            .accessDeniedHandler(accessDeniedHandler)
            // enable frame
            .and().headers().frameOptions().sameOrigin()
            // create no session
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // authenticate rules
            .and().authorizeRequests()
            .requestMatchers(requestMatchers.toArray(requestMatchers.toArray(new PermitAllRequestMatcher[0])))
            .permitAll()
            .anyRequest().authenticated();
        return http.build();
    }

}
