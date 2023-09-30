package com.psiquelaboral.psique.quiz.infrastructure.mapper;

import com.psiquelaboral.psique.quiz.domain.model.Question;
import com.psiquelaboral.psique.quiz.domain.model.option.BooleanOption;
import com.psiquelaboral.psique.quiz.domain.model.option.NumberOption;
import com.psiquelaboral.psique.quiz.domain.model.option.Option;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.QuestionEntity;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option.BooleanOptionEntity;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option.NumberOptionEntity;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.option.OptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
    componentModel = "spring"
    /*uses = {OptionMapper.class}*/
)
public interface QuestionMapper {

    @Mapping(target = "options", source = "options", qualifiedByName = "optionEntityToModel")
    Question toModel(QuestionEntity entity);

    @Mapping(target = "options", source = "options", qualifiedByName = "optionModelToEntity")
    QuestionEntity toEntity(Question model);

    @Named("optionEntityToModel")
    static Option<?> optionEntityToModel(OptionEntity<?> entity) {
        if (entity instanceof BooleanOptionEntity) {
            Option<Boolean> booleanOption = new BooleanOption();
            booleanOption.setId(entity.getId());
            booleanOption.setLabel(entity.getLabel());
            booleanOption.setValue(((BooleanOptionEntity) entity).getValue());
            return booleanOption;
        }

        if (entity instanceof NumberOptionEntity) {
            Option<Integer> numberOption = new NumberOption();
            numberOption.setId(entity.getId());
            numberOption.setLabel(entity.getLabel());
            numberOption.setValue(((NumberOptionEntity) entity).getValue());
            return numberOption;
        }

        return null;
    }

    @Named("optionModelToEntity")
    static OptionEntity<?> optionModelToEntity(Option<?> model) {
        if (model instanceof BooleanOption) {
            OptionEntity<Boolean> booleanOptionEntity = new BooleanOptionEntity();
            booleanOptionEntity.setId(model.getId());
            booleanOptionEntity.setLabel(model.getLabel());
            booleanOptionEntity.setValue(((BooleanOption) model).getValue());
            return booleanOptionEntity;
        }

        if (model instanceof NumberOption) {
            OptionEntity<Integer> numberOptionEntity = new NumberOptionEntity();
            numberOptionEntity.setId(model.getId());
            numberOptionEntity.setLabel(model.getLabel());
            numberOptionEntity.setValue(((NumberOption) model).getValue());
            return numberOptionEntity;
        }

        return null;
    }
}
