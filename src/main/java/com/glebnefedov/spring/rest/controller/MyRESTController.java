package com.glebnefedov.spring.rest.controller;

import com.glebnefedov.spring.rest.entity.Employee;
import com.glebnefedov.spring.rest.exception_handling.EmployeeIncorrectData;
import com.glebnefedov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.glebnefedov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployees=employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if(employee==null){
            throw new NoSuchEmployeeException("There is no Employee with id = "+id+" in Data Base");
        }
        else {
            return employee;
        }

    }
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return  employee;
    }

    @DeleteMapping("/employees/{id}")
    public  String deleteEmployee(@PathVariable int id){
        if(employeeService.getEmployee(id)==null){
            throw new NoSuchEmployeeException("There is no Employee with id= "+id+"in Data Base ");
        }
        else
            employeeService.deleteEmployee(id);

        return "employee with id= "+id+"has benn deleted";
    }










}
