package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.Role;

public interface RoleServiceInterface {

    Role saveRole(Role role);
    void addRoleToUser(String username, String role );



}
