package service;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList = new ArrayList(List.of(
            new Employee("James", "Mitch", 4, 144500),
            new Employee("Mila", "Retavich", 1, 74000),
            new Employee("Victor", "Sells", 2, 82402),
            new Employee("Rezeda", "Mukhlieva", 3, 85000),
            new Employee("Elina", "Isaeva", 5, 15000)));

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.size() > 6) {
            throw new EmployeeStorageIsFullException("Память переполнена");
        }
        for (Employee employeer :employeeList) {
            if (employeer.getFirstName().equals(firstName) && employeer.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Добавление уже существующего сотрудника");
            }
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        final Optional<Employee> employeeOptional = employeeList.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findAny();
        return employeeOptional.orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public List<Employee> printEmployees() {
        return employeeList;
    }
}
