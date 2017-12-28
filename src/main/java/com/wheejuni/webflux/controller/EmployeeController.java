package com.wheejuni.webflux.controller;

import com.wheejuni.webflux.model.Employee;
import com.wheejuni.webflux.model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public Flux<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }
}
