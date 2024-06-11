package com.juliomesquita.application.infra.controllers;

import com.juliomesquita.application.domain.dto.EmployeeDto;
import com.juliomesquita.application.domain.dto.EmployeeSearchDto;
import com.juliomesquita.application.domain.enums.SortType;
import com.juliomesquita.application.domain.interfaces.controller.SearchController;
import com.juliomesquita.application.infra.services.EmployeeService;
import com.juliomesquita.application.infra.utils.ApiResponses;
import com.juliomesquita.application.infra.utils.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/employees")
@RequiredArgsConstructor
public class SearchControllerImpl implements SearchController {
    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<ApiResponses> findAllEmployees() {
        ApiResponses response = ApiResponses
                .builder()
                .data(this.employeeService.findAllEmployee())
                .message("Employee record retrieved successfully")
                .responseCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, response.responseCode());
    }

    @Override
    public ResponseEntity<ApiResponses> searchEmployees(
            SortType sort, int pageNum, int pageSize, EmployeeSearchDto employeeSearchDto
    ) {
        PageRequestDto pageRequestDto = PageRequestDto.builder().page(pageNum).perPage(pageSize).sort(sort).build();
        Page<EmployeeDto> responseData = this.employeeService.findBySearch(employeeSearchDto, pageRequestDto);

        ApiResponses response = ApiResponses
                .builder()
                .data(responseData)
                .message("Employee record retrieved successfully")
                .responseCode(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, response.responseCode());
    }
}
