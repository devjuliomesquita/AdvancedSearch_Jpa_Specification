package com.juliomesquita.application.infra.services;

import com.juliomesquita.application.domain.common.SearchCriteria;
import com.juliomesquita.application.domain.dto.EmployeeDto;
import com.juliomesquita.application.domain.dto.EmployeeSearchDto;
import com.juliomesquita.application.domain.dto.mapper.EmployeeMapper;
import com.juliomesquita.application.domain.entities.Employee;
import com.juliomesquita.application.domain.enums.SortType;
import com.juliomesquita.application.infra.persistence.EmployeeRepository;
import com.juliomesquita.application.infra.specfications.EmployeeSpecificationBuilder;
import com.juliomesquita.application.infra.utils.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Page<EmployeeDto> findBySearch(EmployeeSearchDto employeeSearchDto, PageRequestDto pageRequestDto){
        Specification<Employee> specification = this.createSpecification(employeeSearchDto);
        PageRequest pageRequest = this.createPageRequest(pageRequestDto.sort(), pageRequestDto.page(), pageRequestDto.perPage());
        return this.employeeRepository.findAll(specification, pageRequest).map(EmployeeMapper::toDto);
    }

    private PageRequest createPageRequest(SortType sortType, Integer page, Integer perPage) {
        Sort.Direction direction;
        if (Objects.equals(sortType, SortType.DESC)) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        Sort sort = Sort.by(direction, "firstNm")
                .and(Sort.by(direction, "lastNm"))
                .and(Sort.by(direction, "department"));
        return PageRequest.of(page, perPage , sort);
    }

    private Specification<Employee> createSpecification(EmployeeSearchDto employeeSearchDto){
        EmployeeSpecificationBuilder EmpSpecbuilder = new EmployeeSpecificationBuilder();
        List<SearchCriteria> criteriaList = employeeSearchDto.getSearchCriteriaList();
        if (!criteriaList.isEmpty()) {
            criteriaList.forEach(criteria -> {
                criteria.setDataOption(employeeSearchDto.getDataOption());
                EmpSpecbuilder.with(criteria);
            });
        }
        return EmpSpecbuilder.build();
    }


}
