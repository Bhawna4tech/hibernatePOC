package com.example.hibernateMapping.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employee")
public class EmployeeEntity implements Serializable
{
    private static final long serialVersionUID = -1798070786993154676L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long employeeId;
    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String firstName;
    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
    private String  lastName;
//
//    // By Default fetch = EAGER
//    @OneToOne(fetch = FetchType.LAZY)
//    private AccountEntity account;

    @OneToMany(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "employee")
//    @JoinColumn(name="EMPLOYEE_ID")
    private Set<AccountEntity> account;
}
