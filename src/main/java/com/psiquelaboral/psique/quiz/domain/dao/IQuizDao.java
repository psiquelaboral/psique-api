package com.psiquelaboral.psique.quiz.domain.dao;

import com.psiquelaboral.psique.quiz.domain.model.Quiz;

import java.util.List;

public interface IQuizDao<K> {
    List<Quiz> listAll();

    Quiz getById(K id);

    void create(Quiz quiz);
}
