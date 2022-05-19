package com.ironhack.openbank_project.repository;

import com.ironhack.openbank_project.model.Account;
import com.ironhack.openbank_project.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
   Account findByPrimaryOwner(AccountHolder accountHolder);
}
