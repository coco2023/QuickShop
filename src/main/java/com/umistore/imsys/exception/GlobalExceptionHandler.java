//package com.umistore.imsys.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "An error occurred during registration.");
//
//        // Optionally log the exception details for internal debugging
//        // logger.error("Registration error: ", ex);
//
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(CustomDuplicateUsernameException.class)
//    public ResponseEntity<Object> handleCustomDuplicateUsernameException(CustomDuplicateUsernameException ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(CustomDuplicateEmailException.class)
//    public ResponseEntity<Object> handleCustomDuplicateEmailException(CustomDuplicateEmailException ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(RoleNotFoundException.class)
//    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    // Other exception handlers...
//   @ExceptionHandler(CustomRoleNotFoundException.class)
//public ResponseEntity<Object> handleCustomRoleNotFoundException(CustomRoleNotFoundException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//        }
//
//}
