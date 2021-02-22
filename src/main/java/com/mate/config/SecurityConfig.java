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
    private static final String[] ADMIN_PATTERNS = new String[]{
            "/users/**"};
    private static final String[] USER_PATTERNS = new String[]{
            "/orders/**", "/shopping-carts/**"};
    private static final String[] PERMIT_ALL_PATTERNS = new String[]{
            "/movies/**", "/movie-sessions/**", "/cinema-halls/**"};
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
                .antMatchers(ADMIN_PATTERNS).access("hasAuthority('ADMIN')")
                .antMatchers(USER_PATTERNS).access("hasAnyAuthority('ADMIN', 'USER')")
                .antMatchers(HttpMethod.POST, PERMIT_ALL_PATTERNS).access("hasAuthority('ADMIN')")
                .antMatchers(HttpMethod.GET, PERMIT_ALL_PATTERNS).permitAll()
                .antMatchers("/register/**").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
