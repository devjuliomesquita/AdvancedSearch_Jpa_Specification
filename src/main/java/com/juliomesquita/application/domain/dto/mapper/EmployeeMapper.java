package com.juliomesquita.application.domain.dto.mapper;

import com.juliomesquita.application.domain.dto.EmployeeDto;
import com.juliomesquita.application.domain.entities.Employee;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeDto dto) {
        return Employee
                .builder()
                .id(dto.id())
                .lastNm(dto.lastNm())
                .firstNm(dto.firstNm())
                .jobNm(dto.jobNm())
                .manager_id(dto.manager_id())
                .hireDt(dto.hireDt())
                .salary(dto.salary())
                .commission(dto.commission())
                .department(DepartamentMapper.toEntity(dto.department()))
                .build();
    }

    public static EmployeeDto toDto(Employee entity) {
        return EmployeeDto
                .builder()
                .id(entity.getId())
                .lastNm(entity.getLastNm())
                .firstNm(entity.getFirstNm())
                .jobNm(entity.getJobNm())
                .manager_id(entity.getManager_id())
                .hireDt(entity.getHireDt())
                .salary(entity.getSalary())
                .commission(entity.getCommission())
                .department(DepartamentMapper.toDto(entity.getDepartment()))
                .build();
    }
}
