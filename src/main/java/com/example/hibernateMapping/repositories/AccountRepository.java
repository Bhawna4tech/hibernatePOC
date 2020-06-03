package com.example.hibernateMapping.repositories;

import com.example.hibernateMapping.models.AccountEntity;
import com.example.hibernateMapping.models.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
