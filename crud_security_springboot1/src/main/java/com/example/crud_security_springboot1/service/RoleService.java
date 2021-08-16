package com.example.crud_security_springboot1.service;




import com.example.crud_security_springboot1.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesList();

    void deleteRole(Role role);

//    Role getRole(String name);

    void saveRole(Role role);

    Role getRoleById(Long id);
}
