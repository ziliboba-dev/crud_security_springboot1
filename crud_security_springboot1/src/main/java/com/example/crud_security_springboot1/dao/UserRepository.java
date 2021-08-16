package com.example.crud_security_springboot1.dao;



import com.example.crud_security_springboot1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByName(String name);

}
