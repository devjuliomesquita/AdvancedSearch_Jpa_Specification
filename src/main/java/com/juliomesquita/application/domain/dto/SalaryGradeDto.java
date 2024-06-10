package com.juliomesquita.application.domain.dto;

import java.util.UUID;

public record SalaryGradeDto(
        UUID id,
        double min_salary,
        double max_salary
) {
}
