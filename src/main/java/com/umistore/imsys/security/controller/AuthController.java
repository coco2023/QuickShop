package com.umistore.imsys.security.controller;

import com.umistore.imsys.security.JwtTokenProvider;
import com.umistore.imsys.security.model.AuthRequest;
//import com.umistore.imsys.security.service.CustomUserDetailsService;
import com.umistore.imsys.security.service.PermissionService;
import com.umistore.imsys.security.service.RoleService;
import com.umistore.imsys.security.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Log4j2
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

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

//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

//    @Autowired
//    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticate(@RequestBody AuthRequest data) {
//        try {
//            String username = data.getUsername();
//            // Authenticate the user using the authentication manager
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, data.getPassword())
//            );
//            // Get the authenticated user's roles and permissions (assuming these methods exist)
//            List<String> roles = roleService.getRoleNamesByUsername(username);
//            List<String> permissions = permissionService.getPermissionsByRoleNames(roles);
//            // Create the JWT token with roles and permissions
//            String token = jwtTokenProvider.createToken(username, roles, permissions);
//
//            Map<Object, Object> model = new HashMap<>();
//            model.put("username", username);
//            model.put("token", token);
//            return ResponseEntity.ok(model);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username/password supplied");
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticate(@RequestBody AuthRequest data) {
//        try {
//            String username = data.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
//            // load the user to get the roles for the token
//            UserDetails userDetails = userService.loadUserByUsername(username);
//            String token = jwtTokenProvider.createToken(username, userDetails.getAuthorities());
//
//            Map<Object, Object> model = new HashMap<>();
//            model.put("username", username);
//            model.put("token", token);
//            return ResponseEntity.ok(model);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username/password supplied");
//        }
//    }

}
