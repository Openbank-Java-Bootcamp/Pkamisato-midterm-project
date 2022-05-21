package com.ironhack.openbank_project.service.interfaces;

import com.ironhack.openbank_project.model.StudentChecking;

public interface StudentCheckingServiceInterface {

    StudentChecking addStudentChecking(StudentChecking studentChecking);
    StudentChecking getStudentChecking(Long id);

    void deleteStudentChecking(Long id);
}
