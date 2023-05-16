package com.psiquelaboral.psique.quiz.infrastructure.springdata.entity;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionEntity {
    private Long id;
    private String text;
    private String label;
    private Boolean value;
}
