package com.psiquelaboral.psique.auth.domain.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthErrorResponse {
    @Builder.Default
    private String type = "about:blanck";
    private String title;
    private Integer status;
    private String details;
}
