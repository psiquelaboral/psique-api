package com.psiquelaboral.psique.quiz.infrastructure.springdata.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("quiz")
public class QuizEntity {
    @Id
    private String id;
    private String companyId;
    private String name;
    private String description;
    private Boolean active;
    private List<QuestionEntity> questions;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
