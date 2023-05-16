package com.psiquelaboral.psique.answer.infrastructure.mapper;

import com.psiquelaboral.psique.answer.domain.model.Answer;
import com.psiquelaboral.psique.answer.infrastructure.springdata.entity.AnswerEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {ResponseMapper.class}
)
public interface AnswerMapper {
    Answer toModel(AnswerEntity entity);

    AnswerEntity toEntity(Answer answer);
}
