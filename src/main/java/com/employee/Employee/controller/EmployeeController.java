package com.employee.Employee.controller;

import com.employee.Employee.dto.EmployeeDTO;
import com.employee.Employee.mapper.EmployeeEntityDTOMapper;
import com.employee.Employee.model.Employee;
import com.employee.Employee.repository.EmployeeRepository;
import com.employee.Employee.service.EmployeeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/emp")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;
    @Autowired
    EmployeeEntityDTOMapper employeeEntityDTOMapper;


    /*Employee API's
     *Roles - Only Delete and get all api Are ADMIN - Credentials verifies
     * Rest - Are USER - credentials verified
     */

    //ADDING EMPLOYEE DETAILS
    @PostMapping("/newEmployee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    //GETTING EMPLOYEE DETAILS
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable int id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    //GETTING ALL EMPLOYEE DETAILS
    @GetMapping("/employee")
    public ResponseEntity<List> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    //UPDATE EXISTING EMPLOYEES
    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //DELETING EXISTING EMPLOYEES
    /*@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ----> Above tag can also be used to provide user with certain role
    ----> Prerequisite is to add @EnableMethodSecurity above Security configuration file
    */
    @DeleteMapping("remove/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable int id) {
        EmployeeDTO emp = getEmployeeByID(id).getBody();
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

}
