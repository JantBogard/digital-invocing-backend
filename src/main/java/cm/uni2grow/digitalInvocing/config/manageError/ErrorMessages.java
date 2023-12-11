package cm.uni2grow.digitalInvocing.config.manageError;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorMessages extends RuntimeException {
    private HttpStatus httpStatus;

    public ErrorMessages(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
