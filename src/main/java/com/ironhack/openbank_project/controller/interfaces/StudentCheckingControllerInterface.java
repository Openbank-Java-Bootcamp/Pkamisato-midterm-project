package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.model.StudentChecking;

public interface StudentCheckingControllerInterface {
    StudentChecking getStudentAccount(Long id);

    void deleteStudentAccount(Long id);
}
