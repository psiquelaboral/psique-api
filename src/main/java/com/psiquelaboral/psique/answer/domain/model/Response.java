package com.psiquelaboral.psique.answer.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.psiquelaboral.psique.quiz.domain.model.Option;
import com.psiquelaboral.psique.quiz.domain.model.Question;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String questionId;
    private String itemId;
    private String questionText;
    private Question.Type answerType;
    private Option selectedOption;
    private LocalDateTime createdAt;
}
