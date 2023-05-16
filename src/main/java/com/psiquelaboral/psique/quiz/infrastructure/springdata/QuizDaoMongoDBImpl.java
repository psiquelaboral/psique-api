package com.psiquelaboral.psique.quiz.infrastructure.springdata;

import com.psiquelaboral.psique.quiz.domain.dao.IQuizDao;
import com.psiquelaboral.psique.quiz.domain.model.Quiz;
import com.psiquelaboral.psique.quiz.infrastructure.mapper.QuizMapper;
import com.psiquelaboral.psique.quiz.infrastructure.springdata.entity.QuizEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class QuizDaoMongoDBImpl implements IQuizDao<String> {

    private final MongoTemplate mongoTemplate;
    private final QuizMapper quizMapper;

    @Override
    public List<Quiz> listAll() {
        return this.mongoTemplate.findAll(QuizEntity.class)
                .stream().map(this.quizMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Quiz getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        QuizEntity entity = mongoTemplate.findOne(query, QuizEntity.class);
        return this.quizMapper.toModel(entity);
    }

    @Override
    public void create(Quiz quiz) {
        QuizEntity entity = this.quizMapper.toEntity(quiz);
        this.mongoTemplate.insert(entity);
        quiz.setId(entity.getId());
    }
}
