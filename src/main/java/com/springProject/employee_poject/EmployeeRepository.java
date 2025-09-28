package com.springProject.employee_poject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // String creatEmployee(Employee employee);
    // List<Employee> readEmployees();
    // boolean deleteEmployee(Long id);
} 
