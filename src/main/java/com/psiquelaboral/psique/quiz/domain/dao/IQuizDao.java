package com.psiquelaboral.psique.quiz.domain.dao;

import com.psiquelaboral.psique.quiz.domain.model.Quiz;

import java.util.List;

public interface IQuizDao {
    List<Quiz> listAll();

    Quiz getById(String id);

    void create(Quiz quiz);
}
