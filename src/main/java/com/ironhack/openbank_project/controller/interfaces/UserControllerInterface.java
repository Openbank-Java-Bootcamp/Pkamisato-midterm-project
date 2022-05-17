package com.ironhack.openbank_project.controller.interfaces;


import com.ironhack.openbank_project.model.User;

import java.util.List;

public interface UserControllerInterface {

    List<User> getUsers();
    void saveUser(User user);
}
