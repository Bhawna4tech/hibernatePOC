package com.example.hibernateMapping.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Account")
public class AccountEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long accountId;

    @Column(name = "ACC_NO", unique = false, nullable = false, length = 100)
    private String  accountNumber;

//    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private EmployeeEntity  employee;


    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private EmployeeEntity employee;
}
