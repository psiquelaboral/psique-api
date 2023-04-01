package com.psiquelaboral.psique.quiz.infrastructure.mapper;

import com.psiquelaboral.psique.quiz.domain.model.Option;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.OptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OptionMapper {
    Option toModel(OptionEntity entity);

    OptionEntity toEntity(Option model);
}
