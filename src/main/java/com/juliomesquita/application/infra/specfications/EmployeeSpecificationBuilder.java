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

        return params.stream()
                .map(EmployeeSpecification::new)
                .reduce((spec1, spec2) -> {
                    SearchCriteria criteria = spec2.getSearchCriteria();
                    return (EmployeeSpecification) (SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                                                ? Specification.where(spec1).and(spec2)
                                                : Specification.where(spec1).or(spec2));
                }).orElse(null);
    }
}
