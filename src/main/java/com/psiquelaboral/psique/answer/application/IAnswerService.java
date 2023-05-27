package com.psiquelaboral.psique.answer.application;

import com.psiquelaboral.psique.answer.domain.model.Answer;
import com.psiquelaboral.psique.answer.domain.model.Response;

public interface IAnswerService<K> {
    void initializeAnswer(Answer answer);

    void registryAnswer(K answerId, Response selectedResponse);

    Answer retrieveAnswer(K quizId, K employeeId);

    Answer retrieveAnswer(K answerId);

    Answer finalizeAnswer(K answerId);
}
