package com.example.employeerestapi.DTO;

import com.example.employeerestapi.entities.Employee;

public class EmployeeResponseDto {

        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String department;
        private String designation;
        private double salary;
        private boolean status;

        public EmployeeResponseDto() {
        }

        public static EmployeeResponseDto fromEntity(Employee employee) {
            EmployeeResponseDto dto = new EmployeeResponseDto();

            dto.setId(employee.getId());
            dto.setFirstName(employee.getFirstName());
            dto.setLastName(employee.getLastName());
            dto.setEmail(employee.getEmail());
            dto.setPhoneNumber(employee.getPhoneNumber());
            dto.setDepartment(employee.getDepartment());
            dto.setDesignation(employee.getDesignation());
            dto.setSalary(employee.getSalary());
            dto.setStatus(employee.isStatus());

            return dto;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
}
