package jpabook.jpashop.api.advice;

import jpabook.jpashop.api.MemberApiController;
import jpabook.jpashop.domain.exception.DuplicatedUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "jpabook.jpashop.api")
public class ApiMemberControllerAdvice {

    @ExceptionHandler({DuplicatedUsernameException.class})
    public ResponseEntity<?> duplicatedUsernameExceptionHandler(DuplicatedUsernameException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> validExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        ex.getAllErrors().forEach((e) -> {
            FieldError temp = (FieldError) e;
            errors.put(temp.getField(), temp.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
