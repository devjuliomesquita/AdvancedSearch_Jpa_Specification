package com.juliomesquita.application.infra.services;

import com.juliomesquita.application.domain.dto.DepartmentDto;
import com.juliomesquita.application.domain.dto.mapper.DepartamentMapper;
import com.juliomesquita.application.domain.entities.Department;
import com.juliomesquita.application.infra.persistence.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentDto addDepartment(DepartmentDto request) {
        Department entity = DepartamentMapper.toEntity(request);
        return DepartamentMapper.toDto(this.departmentRepository.saveAndFlush(entity));
    }
}
