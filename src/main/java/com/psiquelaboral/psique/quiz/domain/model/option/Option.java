package com.psiquelaboral.psique.quiz.domain.model.option;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = BooleanOption.class, name = "booleanOption"),
    @JsonSubTypes.Type(value = NumberOption.class, name = "numberOption"),
})
public abstract class Option<VALUE> {
    private Long id;
    private String label;
    private String type;

    public abstract VALUE getValue();

    public abstract void setValue(VALUE value);
}
