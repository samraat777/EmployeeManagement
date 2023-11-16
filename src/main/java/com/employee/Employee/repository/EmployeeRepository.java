package com.employee.Employee.repository;

import com.employee.Employee.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
