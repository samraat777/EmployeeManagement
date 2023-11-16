package com.employee.Employee.service.impl;

import com.employee.Employee.dto.EmployeeDTO;
import com.employee.Employee.mapper.EmployeeEntityDTOMapper;
import com.employee.Employee.model.Employee;
import com.employee.Employee.repository.EmployeeRepository;
import com.employee.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeEntityDTOMapper employeeEntityDTOMapper;

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return employeeEntityDTOMapper.employeeListEntityTODTO(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeEntityDTOMapper.EmployeeDTOToEntity(employeeDTO));
        return employeeDTO;
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %d not found", id));
        }
        return employeeEntityDTOMapper.EmployeeEntityToDTO(employeeRepository.findById(id).get());
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employee, int id) {
        EmployeeDTO currEmployee = getEmployeeById(id);
        Employee employeeExistingDetails = employeeEntityDTOMapper.EmployeeDTOToEntity(currEmployee);
        Employee employeeUpdatedDetails = employeeEntityDTOMapper.EmployeeDTOToEntity(employee);
        employeeExistingDetails.setName(employeeUpdatedDetails.getName());
        employeeExistingDetails.setSalary(employeeUpdatedDetails.getSalary());
        employeeRepository.save(employeeExistingDetails);
        return employeeEntityDTOMapper.EmployeeEntityToDTO(employeeExistingDetails);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
