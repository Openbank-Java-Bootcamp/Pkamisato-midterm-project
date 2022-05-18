package com.ironhack.openbank_project.repository;

import com.ironhack.openbank_project.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking,Long> {
}
