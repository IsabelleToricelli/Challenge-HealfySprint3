package br.com.healfy.ChallengeHealfySOA.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class GlobalErrorHandler {

    //Tratar erro 404 quando o ID não for encontrado
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity dealingError404(){
        return ResponseEntity.notFound().build();
    }

    //Tratar erro 404 quando o campo é inválido

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity dealingError404(MethodArgumentNotValidException ex ){

        List<FieldError> errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DateBadRequest::new).toList());

    }



    private record DateBadRequest(String campo, String mensagem ){

        public DateBadRequest(FieldError errors) {

            this(errors.getField(), errors.getDefaultMessage());

        }

    }

}
