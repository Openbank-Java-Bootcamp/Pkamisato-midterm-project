package com.ironhack.openbank_project.controller.interfaces;


import com.ironhack.openbank_project.DTO.RoleToUserDTO;
import com.ironhack.openbank_project.model.Role;

public interface RoleControllerInterface {

    void saveRole(Role role);
    void addRoleToUserDTO(RoleToUserDTO roleToUserDTO);
}
