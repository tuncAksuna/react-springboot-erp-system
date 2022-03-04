package com.tunCode.backend;

import com.tunCode.backend.model.Employee;
import com.tunCode.backend.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        logger.info("APPLICATION IS RUNNING");

    }


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setFirstName("Cem Tunç");
        employee.setLastName("Aksuna");
        employee.setEmailId("aksuna.tunc@gmail.com");

        employeeRepository.save(employee);

    }
}
