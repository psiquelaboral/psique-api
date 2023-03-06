package com.psiquelaboral.psique.user.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PsiqueUser {
  private String id;
  private String name;
  private String email;
  private String password;
  private List<Role> roles;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;
}
