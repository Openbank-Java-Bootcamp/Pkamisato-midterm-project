package com.ironhack.openbank_project.controller.interfaces;

import com.ironhack.openbank_project.model.StudentChecking;

public interface StudentCheckingControllerInterface {
    StudentChecking getStudentAccount(Long id);
    StudentChecking updateStudentAccount(Long id,StudentChecking studentChecking);
    void deleteStudentAccount( Long id);
}
