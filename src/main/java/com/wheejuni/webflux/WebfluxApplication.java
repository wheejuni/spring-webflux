package com.wheejuni.webflux;

import com.wheejuni.webflux.model.Employee;
import com.wheejuni.webflux.model.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class WebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Bean
	CommandLineRunner saveEmployees(EmployeeRepository employeeRepository) {
		return args -> {
		  employeeRepository.deleteAll().subscribe(null, null, () -> {
              Stream.of(new Employee(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "최정", 2000000L),
                      new Employee(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "김강민", 2000000L),
                      new Employee(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "박재상", 2000000L),
                      new Employee(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE, "서진용", 2000000L)
              ).forEach(e -> employeeRepository.save(e)
                      .subscribe(System.out::println));
          });
        };
	}
}
