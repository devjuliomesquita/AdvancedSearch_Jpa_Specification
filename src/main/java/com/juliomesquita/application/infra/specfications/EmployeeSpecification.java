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
            case CONTAINS -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.like(
                            criteriaBuilder.lower(departmentJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%"
                    );
                }
                yield criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%"
                );

            }
            case DOES_NOT_CONTAIN -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.notLike(
                            criteriaBuilder.lower(departmentJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%"
                    );
                }
                yield criteriaBuilder.notLike(
                        criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%"
                );
            }
            case EQUAL -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.equal(
                            departmentJoin(root).get(searchCriteria.getFilterKey()),
                            searchCriteria.getValue()
                    );
                }
                yield criteriaBuilder.equal(
                        root.get(searchCriteria.getFilterKey()),
                        searchCriteria.getValue()
                );
            }
            case NOT_EQUAL -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.notEqual(
                            departmentJoin(root).get(searchCriteria.getFilterKey()),
                            searchCriteria.getValue()
                    );
                }
                yield criteriaBuilder.notEqual(
                        root.get(searchCriteria.getFilterKey()),
                        searchCriteria.getValue()
                );
            }
            case BEGINS_WITH -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.like(
                            criteriaBuilder.lower(departmentJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%"
                    );
                }
                yield criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),
                        strToSearch + "%"
                );
            }
            case DOES_NOT_BEGIN_WITH -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.notLike(
                            criteriaBuilder.lower(departmentJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%"
                    );
                }
                yield criteriaBuilder.notLike(
                        criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),
                        strToSearch + "%"
                );
            }
            case ENDS_WITH -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.like(
                            criteriaBuilder.lower(departmentJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch
                    );
                }
                yield criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),
                        "%" + strToSearch
                );
            }
            case DOES_NOT_END_WITH -> {
                if (searchCriteria.getFilterKey().equals("dp_name")) {
                    yield criteriaBuilder.notLike(
                            criteriaBuilder.lower(departmentJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch
                    );
                }
                yield criteriaBuilder.notLike(
                        criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())),
                        "%" + strToSearch
                );
            }
            case NUL -> criteriaBuilder.isNull(root.get(searchCriteria.getFilterKey()));
            case NOT_NULL -> criteriaBuilder.isNotNull(root.get(searchCriteria.getFilterKey()));
            case GREATER_THAN -> criteriaBuilder.greaterThan(
                    root.get(searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
                    );
            case GREATER_THAN_EQUAL -> criteriaBuilder.greaterThanOrEqualTo(
                    root.get(searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
            );
            case LESS_THAN -> criteriaBuilder.lessThan(
                    root.get(searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
            );
            case LESS_THAN_EQUAL -> criteriaBuilder.lessThanOrEqualTo(
                    root.get(searchCriteria.getFilterKey()),
                    searchCriteria.getValue().toString()
            );
            case ANY, ALL -> null;
        };
    }

    private Join<Employee, Department> departmentJoin(Root<Employee> root) {
        return root.join("department");
    }
}