package com.psiquelaboral.psique.quiz.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {
    private String text;
    private Long itemId;
    private String instructions;
    private Type answerType;
    private List<Option> options;

    public enum Type {
        LIKERT,
        YES_NO
    }
}
