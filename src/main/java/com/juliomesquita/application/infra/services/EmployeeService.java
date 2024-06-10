package com.juliomesquita.application.infra.services;

import com.juliomesquita.application.domain.dto.EmployeeDto;
import com.juliomesquita.application.domain.dto.mapper.EmployeeMapper;
import com.juliomesquita.application.domain.entities.Employee;
import com.juliomesquita.application.infra.persistence.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDto addEmployee(EmployeeDto request){
        Employee entity = EmployeeMapper.toEntity(request);
        return EmployeeMapper.toDto(this.employeeRepository.saveAndFlush(entity));
    }

    public List<EmployeeDto> findAllEmployee(){
        return this.employeeRepository.findAll().stream().map(EmployeeMapper::toDto).toList();
    }

    public Page<EmployeeDto> findBySearch(Specification<Employee> spec, Pageable pageable){
        return this.employeeRepository.findAll(spec, pageable).map(EmployeeMapper::toDto);
    }
}
