package proyecto.proyecto.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    @SuppressWarnings("unused")
    private static final long serialVersionUid = 1L;

    public ResourceNotFoundException(String message, String string, Integer id){
        super(message);
    }

}