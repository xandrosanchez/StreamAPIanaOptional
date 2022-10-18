package model;

import java.util.List;
import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private int department;
    private double salary;
    private static int counter;
    private int id;

    public List<Employee> getEmployersList() {
        return EmployersList;
    }

    public void setEmployersList(List<Employee> employersList) {
        EmployersList = employersList;
    }

    private List<Employee> EmployersList = List.of(
            new Employee("James", "Mitch", 4, 144500),
            new Employee("Mila", "Retavich", 1, 74000),
            new Employee("Victor", "Sells", 2, 82402),
            new Employee("Rezeda", "Mukhlieva", 3, 85000),
            new Employee("Elina", "Isaeva", 5, 15000));

    public Employee(String firstName, String lastName, int department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        id = counter;
        counter++;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getDepartment() == employee.getDepartment() && Double.compare(employee.getSalary(), getSalary()) == 0 && getId() == employee.getId() && getFirstName().equals(employee.getFirstName()) && getLastName().equals(employee.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getDepartment(), getSalary(), getId());
    }

    @Override
    public String toString() {
        return "Личный номер:" + id + "\nФИ:" + lastName + " " + lastName + "\nОтдел:" + department + "\nЗарплата:" + salary + "\n";
    }

}
