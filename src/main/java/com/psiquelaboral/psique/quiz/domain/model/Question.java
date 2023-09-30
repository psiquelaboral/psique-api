package com.psiquelaboral.psique.quiz.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.psiquelaboral.psique.quiz.domain.model.option.Option;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {
    private Long id;
    private String text;
    private Long itemId;
    private String instructions;
    private Type answerType;
    private List<Option<?>> options;

    public enum Type {
        LIKERT,
        YES_NO
    }
}
