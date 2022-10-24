package service;

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

    public static Stream<Arguments> provideParamsForAddEmployee() {
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
    @MethodSource("provideParamsForAddEmployee")
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
    void deleteEmployee() {
    }

    @Test
    void findEmployee() {
    }

    @Test
    void printEmployees() {
    }
}