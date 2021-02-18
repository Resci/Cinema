package com.mate.service;

import com.mate.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
