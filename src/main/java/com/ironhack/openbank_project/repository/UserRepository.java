package com.ironhack.openbank_project.repository;

import com.ironhack.openbank_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername (String username);
}