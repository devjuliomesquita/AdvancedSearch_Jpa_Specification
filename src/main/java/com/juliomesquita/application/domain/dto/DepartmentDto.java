package com.juliomesquita.application.domain.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DepartmentDto(
        UUID id,
        String dp_name
) {
}
