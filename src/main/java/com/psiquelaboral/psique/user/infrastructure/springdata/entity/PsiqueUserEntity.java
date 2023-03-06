package com.psiquelaboral.psique.user.infrastructure.springdata.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("psiqueUsers")
public class PsiqueUserEntity {
  @Id
  private String id;
  private String name;
  private String email;
  private String password;
  private List<RoleEntity> roles;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;
}
