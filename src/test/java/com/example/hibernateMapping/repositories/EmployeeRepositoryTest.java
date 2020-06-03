package com.example.hibernateMapping.repositories;


import com.example.hibernateMapping.models.AccountEntity;
import com.example.hibernateMapping.models.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldSaveEmployeeWithoutAccountWithoutTransactional() {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName("Yashika")
                .lastName("Tanwar")
                .build();

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        savedEmployee.setFirstName("Laura...and yanna");

        EmployeeEntity  employeeInDb = employeeRepository.findById(savedEmployee.getEmployeeId()).get();
        assertThat(employeeInDb.getFirstName(),is("Yashika"));
        assertThat(employeeEntity.getFirstName(),is("Laura...and yanna"));
        assertNotNull(savedEmployee.getEmployeeId());
    }

    @Test
    @Transactional
    public void shouldSaveEmployeeWithoutAccount() {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName("Yashika")
                .lastName("Tanwar")
                .build();

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        savedEmployee.setFirstName("Laura...and yanna");

        EmployeeEntity  employeeInDb = employeeRepository.findById(savedEmployee.getEmployeeId()).get();
        assertThat(employeeInDb.getFirstName(),is("Laura...and yanna"));
        assertThat(employeeEntity.getFirstName(),is("Laura...and yanna"));
        assertNotNull(savedEmployee.getEmployeeId());
    }

    @Test
    public void shouldSaveEmployeeWithAccount() {

        AccountEntity accountEntity = AccountEntity.builder().accountNumber("A1").build();
        Set<AccountEntity> set = new HashSet();
        set.add(accountEntity);
        AccountEntity savedAccount = accountRepository.save(accountEntity);
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName("Bhoona")
                .lastName("Kumari")
                .account(set)
                .build();

        //not-null property references a null or transient value : com.example.hibernateMapping.models.AccountEntity.employee; nested exception is org.hibernate.Prop
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        savedEmployee.setFirstName("Laura...and yanna");

        assertThat(employeeEntity.getFirstName(),is("skdjfkldsjf"));
        assertNotNull(savedEmployee.getEmployeeId());
        assertNotNull(savedEmployee.getAccount());
//        assertThat(savedEmployee.getAccount().getAccountNumber(),is("A1"));
    }

//    @Test
//    public void shouldFetchEmployee() {
//        AccountEntity accountEntity = AccountEntity.builder().accountNumber("A1").build();
//        AccountEntity savedAccount = accountRepository.save(accountEntity);
//        EmployeeEntity employeeEntity = EmployeeEntity.builder()
//                .firstName("Yashika")
//                .lastName("Tanwar")
//                .account(savedAccount)
//                .build();
//        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
//
//        //Stack over flow as We have added mapping on both side and also we have providing the EAGER fetch
//        // Works fine If add mapping only in parent side
//        // If we add mappings on both soide with Lazy fetch both on parent and child then
//        //could not initialize proxy org.hibernate.LazyInitializationException: Exception will come
//        EmployeeEntity employee = employeeRepository.findById(savedEmployee.getEmployeeId()).get();
//
//        System.out.println(employee.getEmployeeId());
////        System.out.println(employee.getAccount());
//
//
//        AccountEntity account = accountRepository.findById(savedEmployee.getAccount().getAccountId()).get();
//        System.out.println(account.getAccountId());
////        System.out.println(account.getEmployee());
//
//
////        assertTrue(employeeOptional.isPresent());
////        EmployeeEntity employee = employeeOptional.get();
////        assertThat(employee.getFirstName(),is("Yashika"));
//
//
//        //could not initialize proxy [com.example.hibernateMapping.models.AccountEntity#1] - no Session
//        //org.hibernate.LazyInitializationException:
////        assertThat(employee.getAccount(),is(accountEntity));
//    }

//    @Test
//    public void shouldThrowExceptionWhenEmployeeIsSavedWithUnSavedAccount() {
//        AccountEntity accountEntity = AccountEntity.builder().accountNumber("A1").build();
//        EmployeeEntity employeeEntity = EmployeeEntity.builder()
//                .firstName("Yashika")
//                .lastName("Tanwar")
//                .account(accountEntity)
//                .build();
//
//
//        //Throws error : object references an unsaved transient instance - save the transient instance before flushing
//        // Sol: cascade="all" Hibernate will save the child and then parent (We should not do it)
//        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class, () ->
//                employeeRepository.save(employeeEntity));
//
//    }
}