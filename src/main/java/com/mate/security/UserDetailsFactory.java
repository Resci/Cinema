package com.mate.security;

import com.mate.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsFactory {
    UserDetails create(User user);
}
