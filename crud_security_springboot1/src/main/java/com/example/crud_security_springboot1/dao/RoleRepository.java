package com.example.crud_security_springboot1.dao;



import com.example.crud_security_springboot1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
