package org.winterframework.dashboard.security.config;

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
import org.springframework.web.filter.CorsFilter;
import org.winterframework.dashboard.security.core.DynamicWhiteListRequestMatcher;
import org.winterframework.dashboard.security.core.JwtAccessDeniedHandler;
import org.winterframework.dashboard.security.core.JwtAuthenticationEntryPoint;
import org.winterframework.dashboard.security.core.JwtAuthenticationManager;
import org.winterframework.dashboard.security.core.JwtConfigurer;
import org.winterframework.dashboard.security.core.JwtProvider;

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
        return new JwtAuthenticationManager();
    }

    @Profile("dev")
    @Bean
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(
                    "/v2/api-docs",
                    "/swagger-resources/**",
                    "/swagger-ui.html",
                    "/doc.html",
                    "/webjars/**").permitAll();
        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtProvider jwtProvider,
                                           CorsFilter corsFilter,
                                           JwtAuthenticationEntryPoint authenticationErrorHandler,
                                           JwtAccessDeniedHandler jwtAccessDeniedHandler) throws Exception {
        http
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // create no session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(new DynamicWhiteListRequestMatcher())
                .permitAll()

                .anyRequest()
                .authenticated()

                .and()
                .apply(new JwtConfigurer(jwtProvider));

        return http.build();
    }


}
