package com.example.employeerestapi.service;

import com.example.employeerestapi.DTO.EmployeeRequestDto;
import com.example.employeerestapi.DTO.EmployeeResponseDto;
import com.example.employeerestapi.Exception.DuplicateEmailException;
import com.example.employeerestapi.Exception.EmployeeNotFoundException;
import com.example.employeerestapi.entities.Employee;
import com.example.employeerestapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    @Transactional
    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto) {

        Employee employee = dto.convertToEntity();

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmailException(employee.getEmail());
        }

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeResponseDto.fromEntity(savedEmployee);
    }

    // READ ALL
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> showEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponseDto::fromEntity)
                .toList();
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public EmployeeResponseDto showEmployeeById(int id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EmployeeResponseDto.fromEntity(employee);
    }

    // UPDATE
    @Transactional
    public EmployeeResponseDto updateEmployee(
            int id, EmployeeRequestDto dto) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        if (employeeRepository.existsByEmailAndIdNot(
                dto.getEmail(), id)) {
            throw new DuplicateEmailException(dto.getEmail());
        }

        existingEmployee.setFirstName(dto.getFirstName());
        existingEmployee.setLastName(dto.getLastName());
        existingEmployee.setEmail(dto.getEmail());
        existingEmployee.setPhoneNumber(dto.getPhoneNumber());
        existingEmployee.setDepartment(dto.getDepartment());
        existingEmployee.setDesignation(dto.getDesignation());
        existingEmployee.setSalary(dto.getSalary());
        existingEmployee.setStatus(dto.getStatus());

        Employee updatedEmployee =
                employeeRepository.save(existingEmployee);

        return EmployeeResponseDto.fromEntity(updatedEmployee);
    }

    // DELETE
    @Transactional
    public void deleteEmployee(int id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(employee);
    }
}
