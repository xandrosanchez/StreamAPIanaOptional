package service;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import model.Employee;

import java.util.List;

public interface Service {
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    public Employee getLowestPaidEmployee(int department);

    public Employee getHighestPaidEmployee(int department);

    public List<Employee> printEmployeesForDepartment(int department);

    public List<Employee> printEmployeesByDepartments();

    public List<Employee> printEmployees();

}
