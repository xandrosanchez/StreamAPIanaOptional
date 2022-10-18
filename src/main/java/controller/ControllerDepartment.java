package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.Service;

import java.util.List;

@RestController
public class ControllerDepartment {

    private final Service service;

    public ControllerDepartment(Service service) {
        this.service = service;
    }

    @GetMapping(path = "/departments/max-salary")
    public Employee getHighestPaidEmployee(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee;
        employee = getHighestPaidEmployee(departmentId);
        return employee;
    }

    @GetMapping(path = "/departments/min-salary")
    public Employee getLowestPaidEmployee(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee;
        employee = getLowestPaidEmployee(departmentId);
        return employee;
    }

    @GetMapping(path = "/departments/all", params = "departmentId")
    public List<Employee> printEmployeesForDepartment(
            @RequestParam(value = "departmentId") int departmentId) {
        List<Employee> employees;
        employees = printEmployeesForDepartment(departmentId);
        return employees;
    }

    @GetMapping(path = "/departments/all")
    public List<Employee> printEmployeesByDepartments() {
        List<Employee> employees;
        employees = printEmployeesByDepartments();
        return employees;
    }


}
