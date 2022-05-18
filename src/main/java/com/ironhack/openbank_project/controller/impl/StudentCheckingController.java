package com.ironhack.openbank_project.controller.impl;

import com.ironhack.openbank_project.model.StudentChecking;
import com.ironhack.openbank_project.service.interfaces.StudentCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentCheckingController {

    @Autowired
    StudentCheckingServiceInterface studentCheckingServiceInterface;

    @PostMapping("/studentsAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking addStudentAccount(@RequestBody StudentChecking studentChecking){
        return studentCheckingServiceInterface.addStudentChecking(studentChecking);
    }
    @GetMapping("/studentsAccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking getStudentAccount(@PathVariable Long id){
        return studentCheckingServiceInterface.getStudentChecking(id);
    }
    @PutMapping("/studentsAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StudentChecking updateStudentAccount(@PathVariable Long id, @RequestBody StudentChecking studentChecking){
       return studentCheckingServiceInterface.updateStudentChecking(id,studentChecking);
    }
    @DeleteMapping("/studentsAccounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentAccount(@PathVariable Long id){
       studentCheckingServiceInterface.deleteStudentChecking(id);
    }
}
