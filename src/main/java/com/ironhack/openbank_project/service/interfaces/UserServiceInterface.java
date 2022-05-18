package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    List<User> getUsers();

}
