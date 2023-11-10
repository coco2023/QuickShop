package com.umistore.imsys.security.controller;

import com.umistore.imsys.exception.CustomDuplicateEmailException;
import com.umistore.imsys.exception.CustomDuplicateUsernameException;
import com.umistore.imsys.security.dto.RegistrationRequestDTO;
import com.umistore.imsys.security.dto.RegistrationResponseDTO;
import com.umistore.imsys.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegistrationRequestDTO registrationRequestDTO) {

        RegistrationResponseDTO registeredUser = userService.registerNewUser(registrationRequestDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);

//        try {
//            RegistrationResponseDTO registeredUser = userService.registerNewUser(registrationRequestDTO);
//            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
//        } catch (CustomDuplicateUsernameException e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(Collections.singletonMap("message", e.getMessage()));
//        } catch (CustomDuplicateEmailException e) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(Collections.singletonMap("message", e.getMessage()));
//        } catch (Exception e) {
//            // Generic exception handler for other unexpected exceptions
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("message", "An error occurred during registration."));
//        }
    }
}
