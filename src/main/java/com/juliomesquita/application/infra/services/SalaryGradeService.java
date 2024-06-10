package com.juliomesquita.application.infra.services;

import com.juliomesquita.application.domain.dto.SalaryGradeDto;
import com.juliomesquita.application.domain.dto.mapper.SalaryGradeMapper;
import com.juliomesquita.application.domain.entities.SalaryGrade;
import com.juliomesquita.application.infra.persistence.SalaryGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryGradeService {
    private final SalaryGradeRepository salaryGradeRepository;

    public SalaryGradeDto addSalary(SalaryGradeDto request){
        SalaryGrade entity = SalaryGradeMapper.toEntity(request);
        return SalaryGradeMapper.toDto(this.salaryGradeRepository.saveAndFlush(entity));
    }
}
