package com.juliomesquita.application.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_salary_grade")
public class SalaryGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "salary_grade_id", nullable = false)
    private UUID id;

    @Column(name = "salary_grade_min_salary")
    private double min_salary;

    @Column(name = "salary_grade_max_salary")
    private double max_salary;
}
