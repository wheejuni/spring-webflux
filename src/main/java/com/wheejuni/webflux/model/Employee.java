package com.wheejuni.webflux.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode
@ToString
public class Employee {

    @Id
    private long id;

    private String name;
    private long salary;

}
