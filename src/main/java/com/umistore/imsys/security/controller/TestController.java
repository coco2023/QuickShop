package com.umistore.imsys.security.controller;

import com.umistore.imsys.security.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@Log4j2
public class TestController {

    @GetMapping("/customer")
    public ResponseEntity<String> getCustomerPage() {
        return ResponseEntity.ok("Customer page accessed.");
    }

    @GetMapping("/manager")
    public ResponseEntity<String> getManagerPage() {
        return ResponseEntity.ok("Manager page accessed.");
    }

    @PostMapping("/manager")
    public ResponseEntity<String> createManagerResource() {
        return new ResponseEntity<>("Manager resource created.", HttpStatus.CREATED);
    }

}
