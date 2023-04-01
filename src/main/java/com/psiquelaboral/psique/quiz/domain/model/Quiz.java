package com.psiquelaboral.psique.quiz.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quiz {
    private String id;
    private String companyId;
    private String name;
    private String description;
    private Boolean active;
    private List<Question> questions;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
