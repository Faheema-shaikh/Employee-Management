package com.example.employeerestapi.controller;

import com.example.employeerestapi.DTO.EmployeeRequestDto;
import com.example.employeerestapi.DTO.EmployeeResponseDto;
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
    public ResponseEntity<EmployeeResponseDto> addEmployee(
            @Valid @RequestBody EmployeeRequestDto dto) {

        EmployeeResponseDto response =
                employeeService.addEmployee(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    // View All Employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> showEmployee() {

        List<EmployeeResponseDto> employees =
                employeeService.showEmployee();

        return ResponseEntity.ok(employees);
    }

    // View Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> showEmployeeById(
            @PathVariable int id) {

        EmployeeResponseDto response =
                employeeService.showEmployeeById(id);

        return ResponseEntity.ok(response);
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequestDto dto) {

        EmployeeResponseDto response =
                employeeService.updateEmployee(id, dto);

        return ResponseEntity.ok(response);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable int id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}
