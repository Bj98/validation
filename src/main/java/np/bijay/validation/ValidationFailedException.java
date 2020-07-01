package np.bijay.validation;


import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;



import java.util.HashMap;


public class ValidationFailedException extends RuntimeException {

    HashMap<String, String> errormap = new HashMap<>();

    public ValidationFailedException(String message, Errors errors) {
        super(message);
        errors.getAllErrors()
                .forEach(error -> {
                    FieldError fieldError=(FieldError) error;
                    errormap.put(fieldError.getField(), error.getDefaultMessage());
                });

    }

    public HashMap<String, String> getErrors() {
        return errormap;
    }

    public ValidationFailedException(String key, String message) {
        super(message);
        errormap.put(key, message);
    }
}
