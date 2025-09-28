package com.springProject.employee_poject;

import java.util.List;

public interface EmployeeService {
    String creatEmployee(Employee employee);
    List<Employee> readEmployees();
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
    Employee readEmployee(Long id);
}
