package service;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    private final List<Employee> EmployersList = List.of(
            new Employee("James", "Mitch", 4, 144500),
            new Employee("Mila", "Retavich", 1, 74000),
            new Employee("Victor", "Sells", 2, 82402),
            new Employee("Rezeda", "Mukhlieva", 3, 85000),
            new Employee("Elina", "Isaeva", 5, 15000));

    @Override
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employeer = new Employee(firstName, lastName);
        if (EmployersList.size() > 480) {
            throw new EmployeeStorageIsFullException("Память переполнена");
        }
        for (Employee employee : EmployersList) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Добавление уже существующего сотрудника");
            }
        }
        EmployersList.add(employeer);
        return employeer;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (EmployersList.contains(employee)) {
            EmployersList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        final Optional<Employee> employee = EmployersList.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findAny();
        return employee.orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Employee getLowestPaidEmployee(int department) {
        return EmployersList.stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Employee getHighestPaidEmployee(int department) {
        return EmployersList.stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public List<Employee> printEmployeesForDepartment(int department) {
        return EmployersList.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> printEmployeesByDepartments() {
        return EmployersList.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Employee> printEmployees() {
        return EmployersList;
    }
}
