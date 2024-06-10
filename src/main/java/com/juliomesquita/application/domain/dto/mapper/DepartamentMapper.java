package com.juliomesquita.application.domain.dto.mapper;

import com.juliomesquita.application.domain.dto.DepartmentDto;
import com.juliomesquita.application.domain.entities.Department;

public class DepartamentMapper {
    public static Department toEntity(DepartmentDto dto) {
        return Department
                .builder()
                .id(dto.id())
                .dp_name(dto.dp_name())
                .build();
    }

    public static DepartmentDto toDto(Department entity) {
        return DepartmentDto
                .builder()
                .id(entity.getId())
                .dp_name(entity.getDp_name())
                .build();
    }

}
