package com.psiquelaboral.psique.user.infrastructure.springdata;

import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.infrastructure.mapper.PsiqueUserMapper;
import com.psiquelaboral.psique.user.infrastructure.springdata.entity.PsiqueUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PsiqueUserDaoMongoDBImpl implements IPsiqueUserDao<String> {

    private final MongoTemplate mongoTemplate;
    private final PsiqueUserMapper userMapper;

    @Override
    public void create(PsiqueUser user) {
        PsiqueUserEntity entity = this.userMapper.toEntity(user);
        this.mongoTemplate.insert(entity);
        System.out.println(entity);
        user.setId(entity.getId());
    }

    @Override
    public PsiqueUser getByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        PsiqueUserEntity userEntity = mongoTemplate.findOne(query, PsiqueUserEntity.class);
        return userMapper.toModel(userEntity);
    }

    @Override
    public PsiqueUser getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        PsiqueUserEntity userEntity = this.mongoTemplate.findOne(query, PsiqueUserEntity.class);
        return this.userMapper.toModel(userEntity);
    }

    @Override
    public List<PsiqueUser> listAll() {
        return this.mongoTemplate.findAll(PsiqueUserEntity.class)
                .stream().map(this.userMapper::toModel)
                .collect(Collectors.toList());
    }

}
