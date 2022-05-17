package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.DTO.RoleToUserDTO;
import com.ironhack.openbank_project.controller.interfaces.RoleControllerInterface;
import com.ironhack.openbank_project.model.Role;
import com.ironhack.openbank_project.service.interfaces.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController implements RoleControllerInterface {

    @Autowired
    private RoleServiceInterface roleService;

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole (Role role){
        roleService.saveRole(role);
    }

    @PostMapping("/roles/addtouser")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUserDTO(@RequestBody RoleToUserDTO roleToUserDTO){
        roleService.addRoleToUser(roleToUserDTO.getUsername(),roleToUserDTO.getRoleName());
    }






}
