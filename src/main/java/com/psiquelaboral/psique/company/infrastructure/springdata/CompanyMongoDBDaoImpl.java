package com.psiquelaboral.psique.company.infrastructure.springdata;

import com.psiquelaboral.psique.company.domain.dao.ICompanyDao;
import com.psiquelaboral.psique.company.domain.model.Company;
import com.psiquelaboral.psique.company.infrastructure.mapper.CompanyMapper;
import com.psiquelaboral.psique.company.infrastructure.springdata.entity.CompanyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class CompanyMongoDBDaoImpl implements ICompanyDao<String> {

    private final MongoTemplate mongoTemplate;
    private final CompanyMapper companyMapper;

    @Override
    public void create(Company company) {
        company.setCreatedAt(LocalDateTime.now());
        CompanyEntity entity = this.companyMapper.toEntity(company);
        this.mongoTemplate.insert(entity);
        company.setId(entity.getId());
    }

    @Override
    public void update(Company company) {
           CompanyEntity entity = this.companyMapper.toEntity(company);
           this.mongoTemplate.save(entity);
    }

    @Override
    public Company getById(String companyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(companyId));
        CompanyEntity entity = this.mongoTemplate.findOne(query, CompanyEntity.class);
        return this.companyMapper.toModel(entity);
    }
}
