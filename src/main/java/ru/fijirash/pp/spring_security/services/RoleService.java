package ru.fijirash.pp.spring_security.services;


import ru.fijirash.pp.spring_security.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(String userRole);

    Role getRoleById(int id);

    void addRole(Role role);
}