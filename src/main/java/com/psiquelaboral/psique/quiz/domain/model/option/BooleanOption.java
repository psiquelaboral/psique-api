package com.psiquelaboral.psique.quiz.domain.model.option;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BooleanOption extends Option<Boolean> {
    private Boolean value;
}
