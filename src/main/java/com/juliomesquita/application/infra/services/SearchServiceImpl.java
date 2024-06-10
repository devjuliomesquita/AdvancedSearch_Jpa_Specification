package com.juliomesquita.application.infra.services;

import com.juliomesquita.application.infra.persistence.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl {
    private final EmployeeRepository employeeRepository;
}
