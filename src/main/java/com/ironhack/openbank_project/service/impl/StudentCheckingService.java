package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.StudentChecking;
import com.ironhack.openbank_project.repository.StudentCheckingRepository;
import com.ironhack.openbank_project.service.interfaces.StudentCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StudentCheckingService implements StudentCheckingServiceInterface {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public StudentChecking addStudentChecking(StudentChecking studentChecking){
        return studentCheckingRepository.save(studentChecking);
    }
    public StudentChecking getStudentChecking(Long id){
        Optional<StudentChecking> foundStudentChecking = studentCheckingRepository.findById(id);
        if(foundStudentChecking.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Student Checking Account found with ID:"+ id);
        }else{
            return studentCheckingRepository.findById(id).get();
        }
    }

    public void deleteStudentChecking(Long id){
        Optional<StudentChecking> foundStudentChecking = studentCheckingRepository.findById(id);
        if(foundStudentChecking.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Student Checking Account found with ID:"+ id);
        }else{
        studentCheckingRepository.delete(foundStudentChecking.get());
        }
    }
}
