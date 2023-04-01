package com.psiquelaboral.psique.quiz.infrastructure.mapper;

import com.psiquelaboral.psique.quiz.domain.model.Quiz;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.QuizEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {QuestionMapper.class}
)
public interface QuizMapper {
    Quiz toModel(QuizEntity entity);

    QuizEntity toEntity(Quiz model);
}
