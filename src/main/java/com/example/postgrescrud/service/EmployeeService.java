package com.example.postgrescrud.service;

import com.example.postgrescrud.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    // Create
    Employee saveEmployee(Employee employee);

    // Read
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Optional<Employee> getEmployeeByEmail(String email);
    List<Employee> getEmployeesByDepartment(String department);

    // Update
    Employee updateEmployee(Long id, Employee employeeDetails);

    // Delete
    void deleteEmployee(Long id);

    // Additional methods
    List<Employee> getEmployeesBySalaryGreaterThan(double salary);
    List<Employee> searchEmployeesByFirstName(String firstName);
}