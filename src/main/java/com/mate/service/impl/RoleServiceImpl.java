package com.mate.service.impl;

import com.mate.dao.RoleDao;
import com.mate.exception.DataProcessingException;
import com.mate.model.Role;
import com.mate.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name).orElseThrow(
                () -> new DataProcessingException("Role with name " + name + " not found"));
    }
}
