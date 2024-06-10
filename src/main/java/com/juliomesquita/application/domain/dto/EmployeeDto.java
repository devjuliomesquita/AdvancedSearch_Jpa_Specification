package com.juliomesquita.application.domain.dto;

import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record EmployeeDto(
        UUID id,
        String lastNm,
        String firstNm,
        String jobNm,
        Long manager_id,
        Date hireDt,
        double salary,
        double commission,
        DepartmentDto department
) {
}
