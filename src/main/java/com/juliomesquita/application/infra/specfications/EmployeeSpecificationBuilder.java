package com.juliomesquita.application.infra.specfications;

import com.juliomesquita.application.domain.common.SearchCriteria;
import com.juliomesquita.application.domain.entities.Employee;
import com.juliomesquita.application.domain.enums.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecificationBuilder {
    private final List<SearchCriteria> params;

    public EmployeeSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final EmployeeSpecificationBuilder with(
            String key,
            Object value,
            String operation
    ) {
        params.add(new SearchCriteria(key, value, operation));
        return this;
    }

    public final EmployeeSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<Employee> build() {
        if (params.isEmpty()) {
            return null;
        }

        Specification<Employee> specification = new EmployeeSpecification(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            SearchCriteria criteria = params.get(i);
            specification = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(specification).and(new EmployeeSpecification(params.get(i)))
                    : Specification.where(specification).or(new EmployeeSpecification(params.get(i)));
        }
        return specification;
    }
}
