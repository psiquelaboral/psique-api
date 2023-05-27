package com.psiquelaboral.psique.answer.domain.model;

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
public class Answer {
    private String id;
    private String quizId;
    private String employeeId;
    private String name;
    private String description;
    private Status status;
    private List<Response> responses;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public enum Status {
        DONE,
        IN_PROGRESS
    }
}
