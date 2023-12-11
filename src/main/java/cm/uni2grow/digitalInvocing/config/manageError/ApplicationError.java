package cm.uni2grow.digitalInvocing.config.manageError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationError {
    private int code;
    private String message;
}
