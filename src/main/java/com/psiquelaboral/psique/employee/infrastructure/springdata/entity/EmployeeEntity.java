package com.psiquelaboral.psique.employee.infrastructure.springdata.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("employees")
public class EmployeeEntity {
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
