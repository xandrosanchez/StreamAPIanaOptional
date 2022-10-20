package service;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    public List<Employee> printEmployees();

}
