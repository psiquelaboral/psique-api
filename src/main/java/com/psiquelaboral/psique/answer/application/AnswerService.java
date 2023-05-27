package com.psiquelaboral.psique.answer.application;

import com.psiquelaboral.psique.answer.domain.dao.IAnswerDao;
import com.psiquelaboral.psique.answer.domain.model.Answer;
import com.psiquelaboral.psique.answer.domain.model.Response;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AnswerService implements IAnswerService<String> {

    private final IAnswerDao<String> answerDao;

    @Override
    public void initializeAnswer(Answer answer) {
        answer.setCreatedAt(LocalDateTime.now());
        answer.setStatus(Answer.Status.IN_PROGRESS);
        this.answerDao.create(answer);
    }

    @Override
    public void registryAnswer(String answerId, Response selectedResponse) {
        selectedResponse.setCreatedAt(LocalDateTime.now());
        this.answerDao.updateResponses(answerId, selectedResponse);
    }

    @Override
    public Answer retrieveAnswer(String quizId, String employeeId) {
        return this.answerDao.getByQuizIdAndEmployeeId(quizId, employeeId);
    }

    @Override
    public Answer retrieveAnswer(String answerId) {
        return this.answerDao.getById(answerId);
    }

    @Override
    public Answer finalizeAnswer(String answerId) {
        this.answerDao.updateAnswerStatus(answerId, Answer.Status.DONE);
        return this.answerDao.getById(answerId);
    }
}
