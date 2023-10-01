package com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BooleanOptionEntity extends OptionEntity<Boolean> {
    private Boolean value;
}
