package com.ahl.jwt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
