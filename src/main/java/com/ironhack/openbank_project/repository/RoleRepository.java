package com.ironhack.openbank_project.repository;

import com.ironhack.openbank_project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String rolename);
}
