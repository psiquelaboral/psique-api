package com.psiquelaboral.psique.quiz.application;

import com.psiquelaboral.psique.quiz.domain.model.Quiz;

import java.util.List;

public interface IQuizService {
    List<Quiz> listAll();

    Quiz getById(String id);

    void create(Quiz quiz);
}
