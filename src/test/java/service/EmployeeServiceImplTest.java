package service;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private final EmployeeServiceImpl service = new EmployeeServiceImpl();

    public static Stream<Arguments> provideParams() {
        return Stream.of(
                Arguments.of("Ron", "Milis", "RonMilis"),
                Arguments.of("John", "Roy", "JohnRoy"),
                Arguments.of("Irina", "Volk", "IrinaVolk")
        );
    }


    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @MethodSource("provideParams")
    void addEmployeeTest(String firstName, String lastName, String expected) {
        Employee employee = service.addEmployee(firstName, lastName);

        assertEquals(expected, employee.getFirstName() + employee.getLastName());
    }

    @Test
    void employeeStorageIsFullExceptionTest() {
        EmployeeStorageIsFullException thrown = Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> {
            service.addEmployee("I", "I");
            service.addEmployee("O", "O");
            service.addEmployee("1", "I");
            service.addEmployee("O", "");
            service.addEmployee("", "I");
            service.addEmployee("", "");
            service.addEmployee("I", "ki");
            service.addEmployee("gul", "O");
        });

        Assertions.assertEquals("exceptions.EmployeeStorageIsFullException: Память переполнена", thrown.toString());
    }

    @Test
    void employeeAlreadyAddedException() {
        EmployeeAlreadyAddedException thrown = Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            service.addEmployee("I", "I");
            service.addEmployee("I", "I");

        });

        Assertions.assertEquals("exceptions.EmployeeAlreadyAddedException: Добавление уже существующего сотрудника", thrown.toString());
    }

    @ParameterizedTest
    @MethodSource("provideParams")
    void deleteEmployee(String firstName, String lastName, String expected) {
        service.addEmployee(firstName, lastName);
        Employee employee = service.deleteEmployee(firstName, lastName);

        assertEquals(expected, employee.getFirstName() + employee.getLastName());
    }

    @Test
    void EmployeeNotFoundExceptionForDelete() {
        EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            service.deleteEmployee("I", "I");

        });

        Assertions.assertEquals("exceptions.EmployeeNotFoundException: Сотрудник не найден", thrown.toString());
    }

    @Test
    void EmployeeNotFoundExceptionForFind() {
        EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            service.findEmployee("I", "I");

        });

        Assertions.assertEquals("exceptions.EmployeeNotFoundException: Сотрудник не найден", thrown.toString());
    }


    @ParameterizedTest
    @MethodSource("provideParams")
    void findEmployee(String firstName, String lastName, String expected) {
        service.addEmployee(firstName, lastName);
        Employee employee = service.findEmployee(firstName, lastName);

        assertEquals(expected, employee.getFirstName() + employee.getLastName());
    }

    @Test
    void printEmployees() {
        String expected = "[Личный номер:0\n" +
                "ФИ:James Mitch\n" +
                "Отдел:4\n" +
                "Зарплата:144500.0\n" +
                ", Личный номер:1\n" +
                "ФИ:Mila Retavich\n" +
                "Отдел:1\n" +
                "Зарплата:74000.0\n" +
                ", Личный номер:2\n" +
                "ФИ:Victor Sells\n" +
                "Отдел:2\n" +
                "Зарплата:82402.0\n" +
                ", Личный номер:3\n" +
                "ФИ:Rezeda Mukhlieva\n" +
                "Отдел:3\n" +
                "Зарплата:85000.0\n" +
                ", Личный номер:4\n" +
                "ФИ:Elina Isaeva\n" +
                "Отдел:5\n" +
                "Зарплата:15000.0\n" +
                "]";
        assertEquals(expected,service.printEmployees());
    }
}