package com.psiquelaboral.psique.quiz.infrastructure.mapper;

import com.psiquelaboral.psique.quiz.domain.model.Question;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {OptionMapper.class}
)
public interface QuestionMapper {
    Question toModel(QuestionEntity entity);

    QuestionEntity toEntity(Question model);
}
