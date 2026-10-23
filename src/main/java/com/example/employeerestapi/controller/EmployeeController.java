package com.example.employeerestapi.controller;

import com.example.employeerestapi.DTO.EmployeeRequestDto;
import com.example.employeerestapi.DTO.EmployeeResponseDto;
import com.example.employeerestapi.entities.Employee;
import com.example.employeerestapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@Tag(
        name = "Employee Management",
        description = "APIs for managing employee records"
)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @Operation(summary = "Create an employee")
    public ResponseEntity<EmployeeResponseDto> addEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        EmployeeResponseDto response =
                employeeService.addEmployee(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeeResponseDto>> showEmployee() {

        return ResponseEntity.ok(employeeService.showEmployee());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an employee by ID")
    public ResponseEntity<EmployeeResponseDto> showEmployeeById(
            @PathVariable int id) {

        return ResponseEntity.ok(employeeService.showEmployeeById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        EmployeeResponseDto response =
                employeeService.updateEmployee(id, requestDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}