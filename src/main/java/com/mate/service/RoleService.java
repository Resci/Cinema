package com.mate.service;

import com.mate.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getByName(String name);
}
