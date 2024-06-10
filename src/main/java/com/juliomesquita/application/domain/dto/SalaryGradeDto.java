package com.juliomesquita.application.domain.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SalaryGradeDto(
        UUID id,
        double min_salary,
        double max_salary
) {
}
