package com.example.streamapianaoptional.controller;

import com.example.streamapianaoptional.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.streamapianaoptional.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RequestMapping("/departments")
@RestController
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }


    @GetMapping(path = "/max-salary")
    public Employee getHighestPaidEmployee(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee;
        employee = service.getHighestPaidEmployee(departmentId);
        return employee;
    }

    @GetMapping(path = "/min-salary")
    public Employee getLowestPaidEmployee(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee;
        employee = service.getLowestPaidEmployee(departmentId);
        return employee;
    }

    @GetMapping(path = "/all", params = "departmentId")
    public List<Employee> printEmployeesForDepartment(
            @RequestParam(value = "departmentId") int departmentId) {
        List<Employee> employees;
        employees = service.printEmployeesForDepartment(departmentId);
        return employees;
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> printEmployeesByDepartments() {
        Map<Integer, List<Employee>> employees;
        employees = service.printEmployeesByDepartments();
        return employees;
    }
}
