package com.ironhack.openbank_project;

import com.ironhack.openbank_project.model.AccountHolder;
import com.ironhack.openbank_project.model.Checking;
import com.ironhack.openbank_project.model.Role;
import com.ironhack.openbank_project.model.User;
import com.ironhack.openbank_project.service.impl.AccountHolderService;
import com.ironhack.openbank_project.service.impl.CheckingService;
import com.ironhack.openbank_project.service.impl.RoleService;
import com.ironhack.openbank_project.service.impl.UserService;
import com.ironhack.openbank_project.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;


import static com.ironhack.openbank_project.enums.Status.ACTIVE;


@SpringBootApplication(scanBasePackages = { "com.ironhack.openbank_project" })
@Profile("!test")
public class OpenbankProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenbankProjectApplication.class, args);
    }

    @Autowired
    public Environment environment;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService, AccountHolderService accountHolderService, CheckingService checkingService) {
        return args -> {
            if(!Arrays.asList(environment.getActiveProfiles()).contains("test")){
                roleService.saveRole(new Role(null, "ROLE_ACCOUNT_HOLDER"));
                roleService.saveRole(new Role(null, "ROLE_ADMIN"));

                userService.saveUser(new User(null, "John Doe", "john", "1234", new ArrayList<>()));
                userService.saveUser(new User(null, "James Smith", "james", "1234", new ArrayList<>()));
                userService.saveUser(new User(null, "Jane Carry", "jane", "1234", new ArrayList<>()));
                userService.saveUser(new User(null, "Chris Anderson", "chris", "1234", new ArrayList<>()));


                roleService.addRoleToUser("john", "ROLE_USER");
                roleService.addRoleToUser("james", "ROLE_ADMIN");
                roleService.addRoleToUser("jane", "ROLE_USER");
                roleService.addRoleToUser("chris", "ROLE_ADMIN");
                roleService.addRoleToUser("chris", "ROLE_USER");

                userService.saveUser(new AccountHolder("bill", "bill", "1234", null, LocalDate.now(), null, "dfsgdfg@gmail.com"));
                userService.saveUser(new AccountHolder("mitu", "mitu", "1234", null, LocalDate.of(1992, 01, 02), null, "p1111@gmail.com"));

            }
        };
    }

}


