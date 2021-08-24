package com.example.crud_security_springboot1.service;




import com.example.crud_security_springboot1.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getRolesList();

    void deleteRole(Role role);

    void saveRole(Role role);

    Role getRoleById(Long id);

    Set<Role> getRolesByName(String[] names);
}
