package com.psiquelaboral.psique.quiz.infrastructure.mapper;

import com.psiquelaboral.psique.quiz.domain.model.option.Option;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option.OptionEntity;

//@Mapper(componentModel = "spring")
public interface OptionMapper {
    Option<?> toModel(OptionEntity<?> entity);

    OptionEntity<?> toEntity(Option<?> model);
}
