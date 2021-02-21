package com.mate.security;

import com.mate.service.UserService;
import java.time.format.DateTimeParseException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final UserDetailsFactory userDetailsFactory;

    public CustomUserDetailsService(UserService userService,
                                    UserDetailsFactory userDetailsFactory) {
        this.userService = userService;
        this.userDetailsFactory = userDetailsFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return userDetailsFactory.create(userService.findByEmail(s));
        } catch (DateTimeParseException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
