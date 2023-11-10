package com.umistore.imsys.security.controller;

import com.umistore.imsys.security.JwtTokenProvider;
import com.umistore.imsys.security.model.AuthRequest;
import com.umistore.imsys.security.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Log4j2
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest data) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            data.getUsername(),
                            data.getPassword()
                    )
            );

            log.info("***authentication: " + authentication);
            // If authentication was successful, set the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate a JWT token with roles and permissions included
            String token = jwtTokenProvider.createToken(authentication);
            log.info("***token: " + token);

            // Return the token and user information as needed
            Map<String, Object> model = new HashMap<>();
            model.put("username", data.getUsername());
            model.put("token", token);
            log.info("***model: " + model);

            return ResponseEntity.ok(model);

        } catch (AuthenticationException e) {
            log.error("Authentication error", e); // Log the exception
            return new ResponseEntity<>("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }

}
