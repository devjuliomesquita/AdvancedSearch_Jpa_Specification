package com.juliomesquita.application.infra.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliomesquita.application.domain.dto.DepartmentDto;
import com.juliomesquita.application.domain.dto.EmployeeDto;
import com.juliomesquita.application.domain.dto.SalaryGradeDto;
import com.juliomesquita.application.infra.services.DepartmentService;
import com.juliomesquita.application.infra.services.EmployeeService;
import com.juliomesquita.application.infra.services.SalaryGradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoPopulateDatabase implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final SalaryGradeService salaryGradeService;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {


            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/department.json");
            List<DepartmentDto> departments = mapper.readValue(inputStream, new TypeReference<>() {
            });
            departments.forEach(this.departmentService::addDepartment);
            log.info("Departamentos salvos com sucesso.");

            inputStream = TypeReference.class.getResourceAsStream("/json/employee.json");
            List<EmployeeDto> employees = mapper.readValue(inputStream, new TypeReference<>() {
            });
            employees.forEach(this.employeeService::addEmployee);
            log.info("Employees salvos com sucesso.");

            inputStream = TypeReference.class.getResourceAsStream("/json/salary_grade.json");
            List<SalaryGradeDto> salariesGrade = mapper.readValue(inputStream, new TypeReference<>() {
            });
            salariesGrade.forEach(this.salaryGradeService::addSalary);
            log.info("Salaries salvos com sucesso.");
        } catch (Exception e) {
            log.error("Problemas ao salvar {}", e.getMessage());
        }
    }
}
