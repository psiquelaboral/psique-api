package com.psiquelaboral.psique.quiz.infrastructure.springdata.entity;

import com.psiquelaboral.psique.quiz.domain.model.Question;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option.OptionEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    private Long id;
    private String text;
    private Long itemId;
    private String instructions;
    private Question.Type answerType;
    private List<OptionEntity<?>> options;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
