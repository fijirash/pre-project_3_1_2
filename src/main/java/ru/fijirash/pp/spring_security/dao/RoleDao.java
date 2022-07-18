package ru.fijirash.pp.spring_security.dao;


import ru.fijirash.pp.spring_security.models.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles();

    Role getRole(String userRole);

    Role getRoleById(int id);

    void addRole(Role role);

}