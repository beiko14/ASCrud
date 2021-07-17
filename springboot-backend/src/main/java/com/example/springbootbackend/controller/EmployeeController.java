package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired //inject the repository by the spring container
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no employee with the following id: " + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
        Employee employeeUpdated = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no employee with the following id: " + id));

        employeeUpdated.setFirstName(employee.getFirstName());
        employeeUpdated.setLastName(employee.getLastName());
        employeeUpdated.setEmailId(employee.getEmailId());

        Employee returningEmployee = employeeRepository.save(employeeUpdated);
        return ResponseEntity.ok(returningEmployee);
    }
}












