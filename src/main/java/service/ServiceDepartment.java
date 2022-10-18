package service;

import exceptions.EmployeeNotFoundException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceDepartment {

    Employee employee = new Employee();

    public Employee getLowestPaidEmployee(int department) {
        return employee.getEmployeeList().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee getHighestPaidEmployee(int department) {
        return employee.getEmployeeList().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public List<Employee> printEmployeesForDepartment(int department) {
        return employee.getEmployeeList().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer,List<Employee>> printEmployeesByDepartments() {
        return employee.getEmployeeList().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
