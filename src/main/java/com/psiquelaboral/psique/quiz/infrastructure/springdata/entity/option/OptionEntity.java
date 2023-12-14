package com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class OptionEntity<VALUE> {
    private Long id;
    private String label;

    public abstract void setValue(VALUE value);

    public abstract VALUE getValue();
}
