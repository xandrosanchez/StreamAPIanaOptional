package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends OutOfMemoryError {
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
