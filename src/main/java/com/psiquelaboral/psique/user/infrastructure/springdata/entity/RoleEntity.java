package com.psiquelaboral.psique.user.infrastructure.springdata.entity;

import com.psiquelaboral.psique.user.domain.model.Role.RoleName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
  private RoleName name;
  private LocalDateTime createdAt;
}
