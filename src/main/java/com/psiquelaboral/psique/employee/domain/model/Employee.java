package com.psiquelaboral.psique.employee.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    private String id;
    private String companyId;
    private Integer age;
    private String name;
    private String email;
    private String picture;
    private Character gender;
    private String job;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
