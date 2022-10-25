package service;

import com.example.streamapianaoptional.exceptions.EmployeeNotFoundException;
import com.example.streamapianaoptional.model.Employee;
import com.example.streamapianaoptional.service.DepartmentService;
import com.example.streamapianaoptional.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl EmployeeService;

    @InjectMocks
    private DepartmentService departmentService;

    public static Stream<Arguments> departmentNumbers() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5)
        );
    }

    private final List<Employee> employeeList = new ArrayList<>(List.of(
            new Employee("James", "Mitch", 4, 144500),
            new Employee("Mila", "Retavich", 1, 74000),
            new Employee("Victor", "Sells", 2, 82402),
            new Employee("Rezeda", "Mukhlieva", 3, 85000),
            new Employee("Elina", "Isaeva", 5, 15000)));


    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void getLowestPaidEmployeeTest(int department) {
        when(EmployeeService.getEmployeeList())
                .thenReturn(employeeList);
        Optional<Employee> expected = employeeList.stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()));
        assertEquals(expected, departmentService.getLowestPaidEmployee(department));
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void getHighestPaidEmployeeTest(int department) {
        when(EmployeeService.getEmployeeList())
                .thenReturn(employeeList);
        Optional<Employee> expected = employeeList.stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()));
        assertEquals(expected, departmentService.getHighestPaidEmployee(department));
    }

    @ParameterizedTest
    @MethodSource("departmentNumbers")
    void printEmployeesForDepartmentTest(int department) {
        when(EmployeeService.getEmployeeList())
                .thenReturn(employeeList);
        List<Employee> expected = employeeList.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        assertEquals(expected, departmentService.printEmployeesForDepartment(department));
    }

    @Test
    void printEmployeesByDepartmentsTest() {
        when(EmployeeService.getEmployeeList())
                .thenReturn(employeeList);
        Map<Integer, List<Employee>> expected = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        assertEquals(expected, departmentService.printEmployeesByDepartments());
    }

    @Test
    void employeeNotFoundExceptionTest(){
        when(EmployeeService.getEmployeeList())
                .thenReturn(null);
        assertThrows(NullPointerException.class,
                () -> departmentService.getLowestPaidEmployee(1));
        assertThrows(NullPointerException.class,
                () -> departmentService.getHighestPaidEmployee(1));
        assertThrows(NullPointerException.class,
                () -> departmentService.printEmployeesForDepartment(1));
    }

}