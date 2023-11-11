package com.employee.Employee.controller;

import com.employee.Employee.dto.EmployeeDTO;
import com.employee.Employee.mapper.EmployeeEntityDTOMapper;
import com.employee.Employee.model.Employee;
import com.employee.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeEntityDTOMapper employeeEntityDTOMapper;

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        employeeRepository.save(employeeEntityDTOMapper.EmployeeDTOToEntity(employeeDTO));
        return new ResponseEntity<>(employeeEntityDTOMapper.EmployeeDTOToEntity(employeeDTO),HttpStatus.CREATED);
    }

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);

    }

    @PostMapping("/test")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }

}
