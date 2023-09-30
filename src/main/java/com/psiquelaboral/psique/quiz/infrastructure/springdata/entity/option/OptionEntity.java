package com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OptionEntity<VALUE> {
    private Long id;
    private String label;

    public void setValue(VALUE value) {
    }

    public VALUE getValue() {
        return null;
    }
}
