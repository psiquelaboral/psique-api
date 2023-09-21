package com.psiquelaboral.psique.company.infrastructure.springdata.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("companies")
public class CompanyEntity {
    private String id;
    private String representativeId;
    private String name;
    private String RFC;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
