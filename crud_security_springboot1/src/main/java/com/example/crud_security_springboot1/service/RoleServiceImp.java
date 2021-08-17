package com.example.crud_security_springboot1.service;


import com.example.crud_security_springboot1.dao.RoleRepository;
import com.example.crud_security_springboot1.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImp implements RoleService {
    RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRolesList() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }


    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = null;
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            role = optionalRole.get();
        }
        return role;
    }
}
