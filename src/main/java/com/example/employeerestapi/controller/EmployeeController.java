package com.example.employeerestapi.controller;

import com.example.employeerestapi.DTO.EmployeeRequestDto;
import com.example.employeerestapi.entities.Employee;
import com.example.employeerestapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @Valid @RequestBody EmployeeRequestDto dto) {

        Employee employee = convertToEntity(dto);
        Employee savedEmployee = employeeService.addEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedEmployee);
    }

    // View All Employees
    @GetMapping
    public ResponseEntity<List<Employee>> showEmployee() {

        List<Employee> employees = employeeService.showEmployee();

        return ResponseEntity.ok(employees);
    }

    // View Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> showEmployeeById(
            @PathVariable int id) {

        Employee employee = employeeService.showEmployeeById(id);

        return ResponseEntity.ok(employee);
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequestDto dto) {

        Employee employeeData = convertToEntity(dto);

        Employee updatedEmployee =
                employeeService.updateEmployee(id, employeeData);

        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable int id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    private Employee convertToEntity(EmployeeRequestDto dto) {

        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        employee.setStatus(dto.getStatus());

        return employee;
    }

}
