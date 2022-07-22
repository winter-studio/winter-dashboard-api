package cn.wintersoft.dashboard.security.config;

import cn.wintersoft.dashboard.security.core.JwtAccessDeniedHandler;
import cn.wintersoft.dashboard.security.core.JwtAuthenticationEntryPoint;
import cn.wintersoft.dashboard.security.core.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import cn.wintersoft.dashboard.security.access.DataPermissionEvaluator;
import cn.wintersoft.dashboard.security.permission.handler.DataPermissionHandler;

import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private ApplicationContext context;

    @Autowired(required = false)
    private List<DataPermissionHandler> handlers;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new DataPermissionEvaluator(handlers));
        expressionHandler.setApplicationContext(context);
        expressionHandler.setDefaultRolePrefix("");
        return expressionHandler;
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
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
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationEntryPoint authenticationErrorHandler,
                                           JwtAccessDeniedHandler accessDeniedHandler,
                                           JwtAuthenticationFilter jwtAuthenticationFilter,
                                           List<PermitAllRequestMatcher> requestMatchers) throws Exception {
        http.csrf().disable().cors().disable()
            // .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
            .authenticationEntryPoint(authenticationErrorHandler).accessDeniedHandler(accessDeniedHandler)
            // enable frame
            .and().headers().frameOptions().sameOrigin()
            // create no session
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // authenticate rules
            .and().authorizeRequests()
            .requestMatchers(requestMatchers.toArray(new PermitAllRequestMatcher[0])).permitAll()
            // admin api
            .anyRequest().authenticated();
        return http.build();
    }

}
