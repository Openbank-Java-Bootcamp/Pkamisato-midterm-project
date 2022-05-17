package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.Role;
import com.ironhack.openbank_project.model.User;
import com.ironhack.openbank_project.repository.RoleRepository;
import com.ironhack.openbank_project.repository.UserRepository;
import com.ironhack.openbank_project.service.interfaces.RoleServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService implements RoleServiceInterface {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public Role saveRole(Role role) {
        log.info("Saving a role {} to the database", role.getName());
        return roleRepository.save(role);
    }


    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }
}



















