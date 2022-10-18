package exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmployeeStorageIsFullException extends OutOfMemoryError {
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
