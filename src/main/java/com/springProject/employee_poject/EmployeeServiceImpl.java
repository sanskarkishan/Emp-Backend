package com.springProject.employee_poject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    //List<Employee> employees = new ArrayList<>();

    @Override
    public String creatEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        
        //employees.add(employee);
        return "Saved Succesfully";
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : employeesList) {
            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());
            emp.setName(employeeEntity.getName());
            emp.setEmail(employeeEntity.getEmail());
            emp.setPhone(employeeEntity.getPhone());
            emp.setDepart(employeeEntity.getDepart());
        
            employees.add(emp);
        }

        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        // employeeRepository.deleteById(id);
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeRepository.delete(employeeEntity);
        return true;
    
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setName(employee.getName());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setPhone(employee.getPhone());
        employeeEntity.setDepart(employee.getDepart());
        employeeRepository.save(employeeEntity);
        return "Updated Succesfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    

    // @Override
    // public boolean deleteEmployee(Long id) {
    //     employees.remove(id);
    //     return true;
    // }

    //@Override
    // public boolean deleteEmployee(Long id) {
    //     List<Employee> filteredEmployees = employees.stream()
    //             .filter(emp -> !emp.getId().equals(id))
    //             .collect(Collectors.toList());

    //     if (filteredEmployees.size() != employees.size()) {
    //         employees = filteredEmployees;
    //         return true;
    //     }
    //     return false;
    // }

}
