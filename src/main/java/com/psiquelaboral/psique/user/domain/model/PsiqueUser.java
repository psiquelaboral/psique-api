package com.psiquelaboral.psique.user.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PsiqueUser {
    private String id;
    private String companyId;
    private String name;
    private String email;
    private String password;
    private String picture;
    @Builder.Default
    private List<Role> roles = new ArrayList<>();
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
