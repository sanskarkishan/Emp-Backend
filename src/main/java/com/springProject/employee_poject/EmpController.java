package com.springProject.employee_poject;

import org.springframework.web.bind.annotation.RestController;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://emp-database-system.netlify.app"
})
public class EmpController {
    
    //EmployeeService employeeService = new EmployeeServiceImpl();
    //Dependency Injection
    @Autowired 
    EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getemployees() {
        
        return employeeService.readEmployees();
    }

    @PostMapping("employees")
    public String postMethodName(@RequestBody Employee employee) {
    
        //employees.add(employee);
        return employeeService.creatEmployee(employee);
    }
    
    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        
        if (employeeService.deleteEmployee(id)){
            return "Succesfully Deleted";
        }
        return "Not Found";
    }

    @PutMapping("employees/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @GetMapping("employees/{id}")
    public Employee getemployeesById(@PathVariable Long id) {
        
        return employeeService.readEmployee(id);
    }
}
