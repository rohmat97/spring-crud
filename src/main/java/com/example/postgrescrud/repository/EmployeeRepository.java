package com.example.postgrescrud.repository;

import com.example.postgrescrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find by email
    Optional<Employee> findByEmail(String email);

    // Find by department
    List<Employee> findByDepartment(String department);

    // Find by salary greater than
    List<Employee> findBySalaryGreaterThan(double salary);

    // Find by first name containing
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    // Custom query methods can be added here as needed
}