package com.psiquelaboral.psique.answer.domain.dao;

import com.psiquelaboral.psique.answer.domain.model.Answer;
import com.psiquelaboral.psique.answer.domain.model.Response;

public interface IAnswerDao<K> {
    void create(Answer answer);

    void updateResponses(K answerId, Response response);

    Answer getByQuizIdAndEmployeeId(K quizId, K employeeId);

    Answer getById(K answerId);

    void updateAnswerStatus(K answerId, Answer.Status status);
}
