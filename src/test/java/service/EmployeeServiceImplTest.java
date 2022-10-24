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

        Assertions.assertEquals("exceptions.EmployeeStorageIsFullException: ������ �����������", thrown.toString());
    }

    @Test
    void employeeAlreadyAddedException() {
        EmployeeAlreadyAddedException thrown = Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            service.addEmployee("I", "I");
            service.addEmployee("I", "I");

        });

        Assertions.assertEquals("exceptions.EmployeeAlreadyAddedException: ���������� ��� ������������� ����������", thrown.toString());
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

        Assertions.assertEquals("exceptions.EmployeeNotFoundException: ��������� �� ������", thrown.toString());
    }

    @Test
    void EmployeeNotFoundExceptionForFind() {
        EmployeeNotFoundException thrown = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            service.findEmployee("I", "I");

        });

        Assertions.assertEquals("exceptions.EmployeeNotFoundException: ��������� �� ������", thrown.toString());
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
        String expected = "[������ �����:0\n" +
                "��:James Mitch\n" +
                "�����:4\n" +
                "��������:144500.0\n" +
                ", ������ �����:1\n" +
                "��:Mila Retavich\n" +
                "�����:1\n" +
                "��������:74000.0\n" +
                ", ������ �����:2\n" +
                "��:Victor Sells\n" +
                "�����:2\n" +
                "��������:82402.0\n" +
                ", ������ �����:3\n" +
                "��:Rezeda Mukhlieva\n" +
                "�����:3\n" +
                "��������:85000.0\n" +
                ", ������ �����:4\n" +
                "��:Elina Isaeva\n" +
                "�����:5\n" +
                "��������:15000.0\n" +
                "]";
        assertEquals(expected,service.printEmployees());
    }
}