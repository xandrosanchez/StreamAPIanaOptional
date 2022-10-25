package com.example.streamapianaoptional.service;

import com.example.streamapianaoptional.exceptions.EmployeeAlreadyAddedException;
import com.example.streamapianaoptional.exceptions.EmployeeNotFoundException;
import com.example.streamapianaoptional.exceptions.EmployeeStorageIsFullException;
import com.example.streamapianaoptional.model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    public List<Employee> printEmployees();

}
