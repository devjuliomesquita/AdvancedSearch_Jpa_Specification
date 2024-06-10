package com.juliomesquita.application.infra.persistence;

import com.juliomesquita.application.domain.entities.SalaryGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalaryGradeRepository extends JpaRepository<SalaryGrade, UUID> {
}
