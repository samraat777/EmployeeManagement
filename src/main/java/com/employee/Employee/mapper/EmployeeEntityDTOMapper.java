package com.employee.Employee.mapper;

import com.employee.Employee.dto.EmployeeDTO;
import com.employee.Employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeEntityDTOMapper {
    @Mapping(target = "empId", source = "employee.id")
    @Mapping(target = "empName", source = "employee.name")
    @Mapping(target = "empSalary", source = "employee.salary")
    EmployeeDTO EmployeeEntityToDTO(Employee employee);
    @Mapping(target = "id", source = "employeeDTO.empId")
    @Mapping(target = "name", source = "employeeDTO.empName")
    @Mapping(target = "salary", source = "employeeDTO.empSalary")
    Employee EmployeeDTOToEntity(EmployeeDTO employeeDTO);

    @Mapping(target = "empId", source = "employee.id")
    @Mapping(target = "empName", source = "employee.name")
    @Mapping(target = "empSalary", source = "employee.salary")
    List<EmployeeDTO> employeeListEntityTODTO(List<Employee> employeeList);


    @Mapping(target = "id", source = "employeeDTO.empId")
    @Mapping(target = "name", source = "employeeDTO.empName")
    @Mapping(target = "salary", source = "employeeDTO.empSalary")
    List<Employee> employeeListDTOToEntity(List<EmployeeDTO> employeeListDTO);


}
