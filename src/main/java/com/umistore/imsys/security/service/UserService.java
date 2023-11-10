package com.umistore.imsys.security.service;

import com.umistore.imsys.constant.UserType;
import com.umistore.imsys.exception.CustomDuplicateEmailException;
import com.umistore.imsys.exception.CustomDuplicateUsernameException;
import com.umistore.imsys.exception.CustomRoleNotFoundException;
import com.umistore.imsys.exception.RoleNotFoundException;
import com.umistore.imsys.security.dto.RegistrationRequestDTO;
import com.umistore.imsys.security.dto.RegistrationResponseDTO;
import com.umistore.imsys.security.entity.*;
import com.umistore.imsys.security.respository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public RegistrationResponseDTO registerNewUser(RegistrationRequestDTO registrationRequestDTO) {

        // Check if username exists
        if (userRepository.existsByUsername(registrationRequestDTO.getUsername())) {
            throw new CustomDuplicateUsernameException("Username is already taken!");
        }

        // Check if email exists
        if (userRepository.existsByEmail(registrationRequestDTO.getEmail())) {
            throw new CustomDuplicateEmailException("Email is already in use!");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(registrationRequestDTO.getPassword());

        // Create a new user
        User newUser = new User();
        newUser.setUsername(registrationRequestDTO.getUsername());
        newUser.setPassword(hashedPassword);
        newUser.setEmail(registrationRequestDTO.getEmail());
        // Safely convert string to UserType
        UserType userType;
        try {
            userType = UserType.valueOf(registrationRequestDTO.getRoleName());
        } catch (IllegalArgumentException e) {
            throw new CustomRoleNotFoundException("Invalid role name provided.");
        }
        newUser.setUserType(UserType.valueOf(registrationRequestDTO.getRoleName()));
        newUser = userRepository.save(newUser);

        // Assign a role to the new user
        Role role = roleRepository.findByRoleName(registrationRequestDTO.getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found with name: " + registrationRequestDTO.getRoleName()));
//                .orElseGet(() -> createNewRole(registrationRequestDTO.getRoleName()));

        UserRole userRole = new UserRole();
        userRole.setUserID(newUser.getUserID());
        userRole.setRoleID(role.getRoleID());
        userRoleRepository.save(userRole);

        RegistrationResponseDTO registrationResponseDTO = RegistrationResponseDTO.builder()
                .userID(newUser.getUserID())
                .username(newUser.getUsername())
                .userType(newUser.getUserType().toString())
                .build();

        return registrationResponseDTO;
    }

    private Role createNewRole(String roleName) {
        Role newRole = new Role();
        newRole.setRoleName(roleName);
        // Optionally set a default description or other properties of the new role
        newRole.setDescription("Default description for " + roleName);
        return roleRepository.save(newRole);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve the user and their roles
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Retrieve roles by the username
        List<String> roleNames = roleRepository.findRoleNamesByUsername(username);

        // Retrieve permissions by the roles
        List<String> permissions = permissionRepository.findPermissionsByRoleNames(roleNames);

        // Combine roles and permissions into authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String roleName : roleNames) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        // Return the user details including the authorities
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    private UserDetails buildUserDetails(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

