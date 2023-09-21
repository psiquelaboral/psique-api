package com.psiquelaboral.psique.employee.infrastructure.springdata;

import com.psiquelaboral.psique.employee.domain.dao.IEmployeeDao;
import com.psiquelaboral.psique.employee.domain.model.Employee;
import com.psiquelaboral.psique.employee.infrastructure.mapper.EmployeeMapper;
import com.psiquelaboral.psique.employee.infrastructure.springdata.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeMongoDBDaoImpl implements IEmployeeDao<String> {

    private final MongoTemplate mongoTemplate;
    private final EmployeeMapper employeeMapper;

    @Override
    public void create(Employee employee) {
        EmployeeEntity entity = this.employeeMapper.toEntity(employee);
        this.mongoTemplate.insert(entity);
        employee.setId(entity.getId());
    }

    @Override
    public void update(Employee employee) {
        EmployeeEntity entity = this.employeeMapper.toEntity(employee);
        this.mongoTemplate.save(entity);
    }

    @Override
    public Employee getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        EmployeeEntity userEntity = this.mongoTemplate.findOne(query, EmployeeEntity.class);
        return this.employeeMapper.toModel(userEntity);
    }

    @Override
    public Employee getByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        EmployeeEntity userEntity = this.mongoTemplate.findOne(query, EmployeeEntity.class);
        return this.employeeMapper.toModel(userEntity);
    }

    @Override
    public List<Employee> listByCompany(String companyId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(companyId));
        List<EmployeeEntity> employees = this.mongoTemplate.find(query, EmployeeEntity.class);
        return employees.stream()
            .map(this.employeeMapper::toModel)
            .toList();
    }
}
