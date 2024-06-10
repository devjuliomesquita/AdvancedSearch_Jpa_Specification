package com.juliomesquita.application.infra.controllers;

import com.juliomesquita.application.domain.common.SearchCriteria;
import com.juliomesquita.application.domain.dto.EmployeeSearchDto;
import com.juliomesquita.application.infra.services.EmployeeService;
import com.juliomesquita.application.infra.services.SearchServiceImpl;
import com.juliomesquita.application.infra.specfications.EmployeeSpecificationBuilder;
import com.juliomesquita.application.infra.utils.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
@RequiredArgsConstructor
public class SearchController {
    private final SearchServiceImpl searchService;
    private final EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<ApiResponses> findAllEmployees() {
        ApiResponses response = ApiResponses
                .builder()
                .data(this.employeeService.findAllEmployee())
                .message("Employee record retrieved successfully")
                .responseCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, response.responseCode());
    }

    @PostMapping(value = "/search")
    public ResponseEntity<ApiResponses> searchEmployees(
            @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestBody EmployeeSearchDto employeeSearchDto
    ) {
        EmployeeSpecificationBuilder builder = new EmployeeSpecificationBuilder();
        List<SearchCriteria> criteriaList = employeeSearchDto.getSearchCriteriaList();
        if (!criteriaList.isEmpty()) {
            criteriaList.forEach(criteria -> {
                criteria.setDataOption(employeeSearchDto.getDataOption());
                builder.with(criteria);
            });
        }
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize,
                Sort.by("firstNm").ascending()
                        .and(Sort.by("lastNm")).ascending()
                        .and(Sort.by("department")).ascending()
        );
        ApiResponses response = ApiResponses
                .builder()
                .data(this.employeeService.findBySearch(builder.build(), pageRequest))
                .message("Employee record retrieved successfully")
                .responseCode(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(response, response.responseCode());
    }
}
