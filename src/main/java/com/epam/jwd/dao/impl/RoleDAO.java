package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Role;

import java.util.List;

public class RoleDAO implements DataAccessObject<Role> {

    private static final RoleDAO INSTANCE = new RoleDAO();

    private RoleDAO() {}

    public static RoleDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Role> readAll() {
        return null;
    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public void insert(Role role) {

    }

    @Override
    public void update(Role role) {

    }
}
