package com.psiquelaboral.psique.answer.infrastructure.mapper;

import com.psiquelaboral.psique.answer.domain.model.Response;
import com.psiquelaboral.psique.answer.infrastructure.springdata.entity.ResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    Response toModel(ResponseEntity entity);

    ResponseEntity toEntity(Response model);
}
