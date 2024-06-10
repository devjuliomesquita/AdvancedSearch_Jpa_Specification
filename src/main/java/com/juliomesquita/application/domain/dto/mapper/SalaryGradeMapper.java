package com.juliomesquita.application.domain.dto.mapper;

import com.juliomesquita.application.domain.dto.SalaryGradeDto;
import com.juliomesquita.application.domain.entities.SalaryGrade;

public class SalaryGradeMapper {
    public static SalaryGrade toEntity(SalaryGradeDto dto) {
        return SalaryGrade
                .builder()
                .id(dto.id())
                .min_salary(dto.min_salary())
                .max_salary(dto.max_salary())
                .build();
    }

    public static SalaryGradeDto toDto(SalaryGrade entity) {
        return SalaryGradeDto
                .builder()
                .id(entity.getId())
                .min_salary(entity.getMin_salary())
                .max_salary(entity.getMax_salary())
                .build();

    }
}