package com.psiquelaboral.psique.user.infrastructure.springdata;

import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.infrastructure.mapper.PsiqueUserMapper;
import com.psiquelaboral.psique.user.infrastructure.springdata.entity.PsiqueUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PsiqueUserMongoDBImpl implements IPsiqueUserDao {

  private final MongoTemplate mongoTemplate;
  private final PsiqueUserMapper userMapper;

  @Override
  public PsiqueUser getByEmail(String email) {
    return null;
  }

  @Override
  public void create(PsiqueUser user) {
    PsiqueUserEntity entity = this.userMapper.toEntity(user);
    this.mongoTemplate.insert(entity);
    System.out.println(entity);
    user.setId(entity.getId());
  }
}
