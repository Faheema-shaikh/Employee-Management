package com.example.employeerestapi.service;

import com.example.employeerestapi.Exception.DuplicateEmailException;
import com.example.employeerestapi.Exception.EmployeeNotFoundException;
import com.example.employeerestapi.entities.Employee;
import com.example.employeerestapi.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmailException(employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    public List<Employee> showEmployee() {
        return employeeRepository.findAll();
    }
    public Employee showEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Transactional
    public Employee updateEmployee(int id, Employee employeeData) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        if (employeeRepository.existsByEmailAndIdNot(
                employeeData.getEmail(), id)) {
            throw new DuplicateEmailException(employeeData.getEmail());
        }

        existingEmployee.setFirstName(employeeData.getFirstName());
        existingEmployee.setLastName(employeeData.getLastName());
        existingEmployee.setEmail(employeeData.getEmail());
        existingEmployee.setPhoneNumber(employeeData.getPhoneNumber());
        existingEmployee.setDepartment(employeeData.getDepartment());
        existingEmployee.setDesignation(employeeData.getDesignation());
        existingEmployee.setSalary(employeeData.getSalary());
        existingEmployee.setStatus(employeeData.isStatus());

        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void deleteEmployee(int id) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.delete(existingEmployee);
    }
}
