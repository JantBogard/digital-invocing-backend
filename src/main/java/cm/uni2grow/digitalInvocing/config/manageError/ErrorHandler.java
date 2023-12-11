package cm.uni2grow.digitalInvocing.config.manageError;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ErrorHandler {
    @ExceptionHandler(ErrorMessages.class)
    public ResponseEntity<ApplicationError> handleProductNotFoundException(ErrorMessages errorMessages,
            WebRequest webRequest) {

        ApplicationError error = new ApplicationError();
        error.setCode(errorMessages.hashCode());
        error.setMessage(errorMessages.getMessage());

        return new ResponseEntity<>(error, errorMessages.getHttpStatus());
    }
}
