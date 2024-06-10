package com.juliomesquita.application.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_id", nullable = false)
    private UUID id;

    @Column(name = "employee_lastNm")
    private String lastNm;

    @Column(name = "employee_firstNm")
    private String firstNm;

    @Column(name = "employee_jobNm")
    private String jobNm;

    @Column(name = "employee_manager_id")
    private Long manager_id;

    @Column(name = "employee_hireDt")
    private Date hireDt;

    @Column(name = "employee_salary")
    private double salary;

    @Column(name = "employee_commission")
    private double commission;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "department_id", name = "department_id")
    private Department department;
}
