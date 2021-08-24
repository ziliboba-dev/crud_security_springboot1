package com.example.crud_security_springboot1.dao;



import com.example.crud_security_springboot1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name in (:roleStringSet)")
    Set<Role> getRolesByName(@Param("roleStringSet") String[] list);
}
