package com.tunCode.backend.controller;

import com.tunCode.backend.configurations.exception.EmployeeAlreadyExistsException;
import com.tunCode.backend.configurations.exception.ResourceNotFoundException;
import com.tunCode.backend.model.Employee;
import com.tunCode.backend.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController {

    private final static String EMPLOYEE_ALREADY_EXISTS = "Employee already exists on the system";
    private final static String EMPLYOEE_NOT_FOUND_BY_ID = "Emplyoee not found";

    @Autowired
    public EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        log.info("Executing getAllEmployees");
        return employeeRepository.findAll();
    }
    @PostMapping("/addEmployee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        log.info("Employee created [{}]", employee.getFirstName() + " " + employee.getLastName() + " " + employee.getEmailId());
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee has not been found"));

        log.info("Executing getEmployeeById , id: [{}]", employee.getId());
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(EMPLYOEE_NOT_FOUND_BY_ID + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);

        log.info("Executing updated employee, Employee: [{}]", employee.getFirstName());
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(EMPLYOEE_NOT_FOUND_BY_ID + id));

        employeeRepository.delete(employee);

        log.info("Executing deleteEmployee, Employee Name : [{}] [{}]", employee.getFirstName(), employee.getLastName());
        return ResponseEntity.ok("Employee successfully deleted from the system : " + employee);
    }
}
