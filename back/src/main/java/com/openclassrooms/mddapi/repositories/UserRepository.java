package com.openclassrooms.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
}
