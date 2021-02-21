package com.mate.dao;

import com.mate.model.Role;
import java.util.Optional;

public interface RoleDao {
    Role add(Role role);

    Optional<Role> getByName(String name);
}
