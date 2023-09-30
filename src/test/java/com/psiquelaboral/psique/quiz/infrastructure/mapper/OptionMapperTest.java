package com.psiquelaboral.psique.quiz.infrastructure.mapper;

import com.psiquelaboral.psique.quiz.domain.dao.IQuizDao;
import com.psiquelaboral.psique.quiz.domain.model.Question;
import com.psiquelaboral.psique.quiz.domain.model.Quiz;
import com.psiquelaboral.psique.quiz.domain.model.option.BooleanOption;
import com.psiquelaboral.psique.quiz.domain.model.option.NumberOption;
import com.psiquelaboral.psique.quiz.domain.model.option.Option;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.QuestionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class OptionMapperTest {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private IQuizDao<String> quizDao;

    @Test
    void polymorphism() {
        Option<Boolean> model = new BooleanOption();
        model.setLabel("soy la opcion 1");
        model.setId(1L);
        model.setValue(true);

        Option<Integer> modelInt = new NumberOption();
        modelInt.setLabel("soy la opcion 1");
        modelInt.setId(1L);
        modelInt.setValue(4);

        Question question = new Question();
        question.setOptions(Arrays.asList(model, modelInt));

        System.out.println(question);

        QuestionEntity entity = this.questionMapper.toEntity(question);
        System.out.println(entity);
    }

    @Test
    void mongodbPolymorphism() {

        Option<Boolean> model = new BooleanOption();
        model.setLabel("soy la opcion 1");
        model.setId(1L);
        model.setValue(true);

        Option<Integer> modelInt = new NumberOption();
        modelInt.setLabel("soy la opcion 2");
        modelInt.setId(2L);
        modelInt.setValue(4);

        Question question = new Question();
        question.setOptions(Arrays.asList(model, modelInt));


        Quiz quiz = new Quiz();
        quiz.setName("Test Polymorphism");
        quiz.setQuestions(List.of(question));

        quizDao.create(quiz);
    }
}