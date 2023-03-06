package com.psiquelaboral.psique.user.infrastructure.mapper;

import com.psiquelaboral.psique.user.domain.model.Role;
import com.psiquelaboral.psique.user.infrastructure.springdata.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface RoleMapper {
    Role toModel(RoleEntity entity);
    RoleEntity toEntity(Role model);
}
