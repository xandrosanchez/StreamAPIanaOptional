package service;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import model.Employee;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    Employee employee = new Employee();


    @Override
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employeer = new Employee(firstName, lastName);
        if (employee.getEmployeeList().size() > 480) {
            throw new EmployeeStorageIsFullException("Память переполнена");
        }
        for (Employee employee : employee.getEmployeeList()) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Добавление уже существующего сотрудника");
            }
        }
        employee.getEmployeeList().add(employeer);
        return employeer;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (employee.getEmployeeList().contains(employee)) {
            employee.getEmployeeList().remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        final Optional<Employee> emploee = employee.getEmployeeList().stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findAny();
        return emploee.orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public List<Employee> printEmployees() {
        return employee.getEmployeeList();
    }
}
