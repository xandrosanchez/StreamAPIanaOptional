package com.example.streamapianaoptional.service;

import com.example.streamapianaoptional.exceptions.EmployeeNotFoundException;
import com.example.streamapianaoptional.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeServiceImpl service;

    public DepartmentService(EmployeeServiceImpl service) {
        this.service = service;
    }

    public Employee getLowestPaidEmployee(int department) {
        return service.getEmployeeList().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee getHighestPaidEmployee(int department) {
        return service.getEmployeeList().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public List<Employee> printEmployeesForDepartment(int department) {
        return service.getEmployeeList().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer,List<Employee>> printEmployeesByDepartments() {
        return service.getEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
