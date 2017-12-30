package com.wheejuni.webflux.controller;

import com.wheejuni.webflux.model.Employee;
import com.wheejuni.webflux.model.EmployeeEvent;
import com.wheejuni.webflux.model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public Flux<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getSingle(@PathVariable long id) {
        return employeeRepository.findById(id);
    }

    @GetMapping("/{id}/events")
    public Flux<EmployeeEvent> getEvents(@PathVariable long id) {
       return employeeRepository.findById(id)
                .flatMapMany(e -> {
                   Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

                   Flux<EmployeeEvent> employeeEventFlux =
                           Flux.fromStream(
                                   Stream.generate(() -> new EmployeeEvent(e, new Date()))
                           );

                   return Flux.zip(interval, employeeEventFlux)
                           .map(Tuple2::getT2);

                });
    }
}
