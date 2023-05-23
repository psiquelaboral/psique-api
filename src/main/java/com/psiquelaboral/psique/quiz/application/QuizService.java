package com.psiquelaboral.psique.quiz.application;

import com.psiquelaboral.psique.quiz.domain.dao.IQuizDao;
import com.psiquelaboral.psique.quiz.domain.model.Quiz;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class QuizService implements IQuizService {

    private final IQuizDao<String> quizDao;

    @Override
    public List<Quiz> listAll() {
        return this.quizDao.listAll();
    }

    @Override
    public Quiz getById(String id) {
        return this.quizDao.getById(id);
    }

    @Override
    public void create(Quiz quiz) {
        quiz.setCreatedAt(LocalDateTime.now());
        this.quizDao.create(quiz);
    }
}
