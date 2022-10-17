package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.Service;

import java.util.List;

@RequestMapping("/employee")
@RestController
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName) {
        try {
            return addEmployee(firstName, lastName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/remove")
    public Employee deleteEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        try {
            return deleteEmployee(firstName, lastName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        try {
            return findEmployee(firstName, lastName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/departments/max-salary")
    public Object getHighestPaidEmployee(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee = null;
        try {
            employee = (Employee) getHighestPaidEmployee(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/min-salary")
    public Object getLowestPaidEmployee(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee = null;
        try {
            employee = (Employee) getLowestPaidEmployee(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/all", params = "departmentId")
    public Object printEmployeesForDepartment(
            @RequestParam(value = "departmentId") int departmentId) {
        List<Employee> employees = null;
        try {
            employees = (List<Employee>) printEmployeesForDepartment(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

    @GetMapping(path = "/departments/all")
    public Object printEmployeesByDepartments() {
        List<Employee> employees = null;
        try {
            employees = (List<Employee>) printEmployeesByDepartments();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

    @GetMapping(path = "/print")
    public Object printEmployees() {
        List<Employee> employees = null;
        try {
            employees = (List<Employee>) printEmployees();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

}
