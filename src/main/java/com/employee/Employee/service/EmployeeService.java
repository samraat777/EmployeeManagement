package com.employee.Employee.service;

import com.employee.Employee.dto.EmployeeDTO;
import com.employee.Employee.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {
    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO saveEmployee(EmployeeDTO employee);

    EmployeeDTO getEmployeeById(int id);

    EmployeeDTO updateEmployee(EmployeeDTO employee, int id);

    void deleteEmployee(int id);
}
