package com.psiquelaboral.psique.company.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
    private String id;
    private String representativeId;
    private String name;
    private String RFC;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
