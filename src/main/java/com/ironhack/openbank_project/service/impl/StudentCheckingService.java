package com.ironhack.openbank_project.service.impl;

import com.ironhack.openbank_project.model.StudentChecking;
import com.ironhack.openbank_project.repository.StudentCheckingRepository;
import com.ironhack.openbank_project.service.interfaces.StudentCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCheckingService implements StudentCheckingServiceInterface {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public StudentChecking addStudentChecking(StudentChecking studentChecking){
        return studentCheckingRepository.save(studentChecking);
    }
    public StudentChecking getStudentChecking(Long id){
        return studentCheckingRepository.findById(id).get();
    }
    public StudentChecking updateStudentChecking(Long id, StudentChecking studentChecking){
        Optional<StudentChecking> studentCheckingFromDb = studentCheckingRepository.findById(id);
        studentChecking.setId(studentCheckingFromDb.get().getId());
        return studentCheckingRepository.save(studentChecking);
    }
    public void deleteStudentChecking(Long id){
        Optional<StudentChecking> foundStudentChecking = studentCheckingRepository.findById(id);
        studentCheckingRepository.delete(foundStudentChecking.get());
    }
}
