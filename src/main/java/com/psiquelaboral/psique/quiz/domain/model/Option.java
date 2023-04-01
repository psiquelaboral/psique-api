package com.psiquelaboral.psique.quiz.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Option {
    private String text;
    private String label;
    private Boolean value;
}
