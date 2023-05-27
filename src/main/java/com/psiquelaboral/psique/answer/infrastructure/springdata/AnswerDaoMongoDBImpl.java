package com.psiquelaboral.psique.answer.infrastructure.springdata;

import com.psiquelaboral.psique.answer.domain.dao.IAnswerDao;
import com.psiquelaboral.psique.answer.domain.model.Answer;
import com.psiquelaboral.psique.answer.domain.model.Response;
import com.psiquelaboral.psique.answer.infrastructure.mapper.AnswerMapper;
import com.psiquelaboral.psique.answer.infrastructure.springdata.entity.AnswerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class AnswerDaoMongoDBImpl implements IAnswerDao<String> {

    private final MongoTemplate mongoTemplate;
    private final AnswerMapper answerMapper;

    @Override
    public void create(Answer answer) {
        AnswerEntity entity = this.answerMapper.toEntity(answer);
        this.mongoTemplate.insert(entity);
        answer.setId(entity.getId());
    }

    @Override
    public void updateResponses(String answerId, Response response) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(answerId));
        Update update = new Update().push("responses", response);
        this.mongoTemplate.updateFirst(query, update, AnswerEntity.class);
    }

    @Override
    public Answer getByQuizIdAndEmployeeId(String quizId, String employeeId) {
        Criteria criteria = Criteria
                .where("quizId").is(quizId)
                .and("employeeId").is(employeeId);
        Query query = new Query().addCriteria(criteria);
        AnswerEntity entity = this.mongoTemplate.findOne(query, AnswerEntity.class);
        return this.answerMapper.toModel(entity);
    }

    @Override
    public Answer getById(String answerId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(answerId));
        AnswerEntity entity = this.mongoTemplate.findOne(query, AnswerEntity.class);
        return this.answerMapper.toModel(entity);
    }

    @Override
    public void updateAnswerStatus(String answerId, Answer.Status status) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(answerId));
        Update update = new Update().set("status", status.toString());
        this.mongoTemplate.updateFirst(query, update, AnswerEntity.class);
    }
}
