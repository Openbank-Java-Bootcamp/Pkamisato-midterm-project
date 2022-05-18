package com.ironhack.openbank_project.repository;

import com.ironhack.openbank_project.model.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings,Long> {
}
