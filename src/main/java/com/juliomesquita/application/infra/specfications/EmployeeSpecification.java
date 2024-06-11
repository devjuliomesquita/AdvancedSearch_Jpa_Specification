package com.juliomesquita.application.infra.specfications;

import com.juliomesquita.application.domain.common.SearchCriteria;
import com.juliomesquita.application.domain.entities.Department;
import com.juliomesquita.application.domain.entities.Employee;
import com.juliomesquita.application.domain.enums.SearchOperation;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class EmployeeSpecification implements Specification<Employee> {
    private final SearchCriteria searchCriteria;

    public EmployeeSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    @Override
    public Predicate toPredicate(
            Root<Employee> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder
    ) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        return switch (Objects.requireNonNull(
                SearchOperation.getSimpleOperation(searchCriteria.getOperation())
        )) {
            case CONTAINS -> criteriaBuilder.like(
                    criteriaBuilder.lower(this.getPathObject(root, searchCriteria.getFilterKey())),
                    "%" + strToSearch + "%"
            );


            case DOES_NOT_CONTAIN -> criteriaBuilder.notLike(
                    criteriaBuilder.lower(this.getPathObject(root, searchCriteria.getFilterKey())),
                    "%" + strToSearch + "%"
            );


            case EQUAL -> criteriaBuilder.equal(
                    this.getPathObject(root, searchCriteria.getFilterKey()),
                    searchCriteria.getValue()
            );

            case NOT_EQUAL -> criteriaBuilder.notEqual(
                    this.getPathObject(root, searchCriteria.getFilterKey()),
                    searchCriteria.getValue()
            );

            case BEGINS_WITH -> criteriaBuilder.like(
                    criteriaBuilder.lower(this.getPathObject(root, searchCriteria.getFilterKey())),
                    strToSearch + "%"
            );

            case DOES_NOT_BEGIN_WITH -> criteriaBuilder.notLike(
                    criteriaBuilder.lower(this.getPathObject(root, searchCriteria.getFilterKey())),
                    strToSearch + "%"
            );

            case ENDS_WITH -> criteriaBuilder.like(
                    criteriaBuilder.lower(this.getPathObject(root, searchCriteria.getFilterKey())),
                    "%" + strToSearch
            );

            case DOES_NOT_END_WITH -> criteriaBuilder.notLike(
                    criteriaBuilder.lower(this.getPathObject(root, searchCriteria.getFilterKey())),
                    "%" + strToSearch
            );

            case NUL -> criteriaBuilder.isNull(this.getPathObject(root, searchCriteria.getFilterKey()));
            case NOT_NULL -> criteriaBuilder.isNotNull(this.getPathObject(root, searchCriteria.getFilterKey()));
            case GREATER_THAN -> criteriaBuilder.greaterThan(
                    this.getPathObject(root, searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
            );
            case GREATER_THAN_EQUAL -> criteriaBuilder.greaterThanOrEqualTo(
                    this.getPathObject(root, searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
            );
            case LESS_THAN -> criteriaBuilder.lessThan(
                    this.getPathObject(root, searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
            );
            case LESS_THAN_EQUAL -> criteriaBuilder.lessThanOrEqualTo(
                    this.getPathObject(root, searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
            );
            case ANY, ALL -> null;
        };
    }

    private Join<Employee, Department> departmentJoin(Root<Employee> root) {
        root.fetch("department");
        return root.join("department");
    }

    private Expression<String> getPathObject(Root<Employee> root, String value) {
        if (value.equals("dp_name")) {
            return departmentJoin(root).get(searchCriteria.getFilterKey());
        }
        return root.get(searchCriteria.getFilterKey());
    }
}