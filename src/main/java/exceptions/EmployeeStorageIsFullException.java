package exceptions;

public class EmployeeStorageIsFullException extends OutOfMemoryError {
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
