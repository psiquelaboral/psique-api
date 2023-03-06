package com.psiquelaboral.psique.user.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
  private String id;
  private RoleName name;
  private LocalDateTime createdAt;

  public Role(RoleName name) {
    this.name = name;
  }

  public enum RoleName{
    ROLE_GOD,
    ROLE_ADMIN,
    ROLE_RH,
    ROLE_EMPLOY
  }
}
