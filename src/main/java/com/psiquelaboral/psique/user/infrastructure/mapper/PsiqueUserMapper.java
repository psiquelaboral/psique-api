package com.psiquelaboral.psique.user.infrastructure.mapper;

import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.infrastructure.springdata.entity.PsiqueUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {RoleMapper.class}
)
public interface PsiqueUserMapper {
    PsiqueUser toModel(PsiqueUserEntity entity);
    PsiqueUserEntity toEntity(PsiqueUser model);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PsiqueUser toUserSummary(PsiqueUser model);
}
