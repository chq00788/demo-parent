package com.chq.demo.security.jwt.common.config;

import com.chq.demo.security.jwt.common.filter.JwtAuthenticationFilter;
import com.chq.demo.security.jwt.common.filter.JwtLoginFilter;
import com.chq.demo.security.jwt.common.handle.AuthEntryPoint;
import com.chq.demo.security.jwt.common.handle.RestAuthenticationAccessDeniedHandler;
import com.chq.demo.security.jwt.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/9
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/user/hello").permitAll()
                .anyRequest().authenticated()
                .anyRequest()
                .access("@rbacAuthorityService.hasPermission(request,authentication)")
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .exceptionHandling().accessDeniedHandler(new RestAuthenticationAccessDeniedHandler())
                .authenticationEntryPoint(new AuthEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
