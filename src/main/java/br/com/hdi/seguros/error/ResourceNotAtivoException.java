package br.com.hdi.seguros.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceNotAtivoException extends RuntimeException {

    public ResourceNotAtivoException(String message) {
        super(message);
    }
}