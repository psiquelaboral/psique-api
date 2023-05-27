package com.psiquelaboral.psique.answer.infrastructure.springdata.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.psiquelaboral.psique.answer.domain.model.Answer;
import com.psiquelaboral.psique.answer.domain.model.Response;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("answer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerEntity {
    @Id
    private String id;
    private String quizId;
    private String employeeId;
    private String name;
    private String description;
    private Answer.Status status;
    private List<Response> responses;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
