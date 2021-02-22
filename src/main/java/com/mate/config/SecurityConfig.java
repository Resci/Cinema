package com.mate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT,"/movie-sessions/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.DELETE,"/movie-sessions/**").access("hasAuthority('ADMIN')")
                .antMatchers("/users/**").access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.POST,
                        "/movies/**",
                        "/movie-sessions/**",
                        "/cinema-halls/**").access("hasAuthority('ADMIN')")
                .antMatchers("/orders/**",
                        "/shopping-carts/**").access("hasAuthority('USER')")
                .antMatchers(HttpMethod.GET,
                        "/movies/**",
                        "/movie-sessions/**",
                        "/cinema-halls/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
