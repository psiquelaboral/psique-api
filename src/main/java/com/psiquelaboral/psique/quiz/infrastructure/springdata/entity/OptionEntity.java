package com.psiquelaboral.psique.quiz.infrastructure.springdata.entity;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionEntity {
    private String text;
    private String label;
    private Boolean value;
}
