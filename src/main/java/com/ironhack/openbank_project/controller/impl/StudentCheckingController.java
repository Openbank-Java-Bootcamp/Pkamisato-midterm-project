package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.controller.interfaces.StudentCheckingControllerInterface;
import com.ironhack.openbank_project.model.StudentChecking;
import com.ironhack.openbank_project.service.interfaces.StudentCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class StudentCheckingController implements StudentCheckingControllerInterface {

    @Autowired
    StudentCheckingServiceInterface studentCheckingServiceInterface;


    @GetMapping("/studentsAccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking getStudentAccount(@PathVariable Long id){
        return studentCheckingServiceInterface.getStudentChecking(id);
    }

    @DeleteMapping("/studentsAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentAccount(@PathVariable Long id){
       studentCheckingServiceInterface.deleteStudentChecking(id);
    }
}
