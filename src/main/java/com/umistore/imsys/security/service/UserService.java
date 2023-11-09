package com.umistore.imsys.security.service;

import com.umistore.imsys.constant.UserType;
import com.umistore.imsys.exception.CustomDuplicateEmailException;
import com.umistore.imsys.exception.CustomDuplicateUsernameException;
import com.umistore.imsys.exception.CustomRoleNotFoundException;
import com.umistore.imsys.security.dto.RegistrationRequestDTO;
import com.umistore.imsys.security.dto.RegistrationResponseDTO;
import com.umistore.imsys.security.entity.Role;
import com.umistore.imsys.security.entity.User;
import com.umistore.imsys.security.entity.UserRole;
import com.umistore.imsys.security.respository.RoleRepository;
import com.umistore.imsys.security.respository.UserRepository;
import com.umistore.imsys.security.respository.UserRoleRepository;
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
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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
//                .orElseThrow(() -> new RoleNotFoundException("Role not found with name: " + registrationRequestDTO.getRoleName()));
                .orElseGet(() -> createNewRole(registrationRequestDTO.getRoleName()));

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
        // Retrieve the User entity by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Retrieve UserRole entities by userID
        List<UserRole> userRoles = userRoleRepository.findByUserID(user.getUserID());

        // Convert UserRole entities to GrantedAuthority objects
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            // Retrieve each Role entity by roleID
            Role role = roleRepository.findByRoleID(userRole.getRoleID())
                    .orElseThrow(() -> new IllegalStateException("Role not found with id: " + userRole.getRoleID()));

            // Create a new GrantedAuthority with the roleName, prepended with "ROLE_"
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }

        UserDetails userDetails = buildUserDetails(user, authorities);

        return userDetails;
    }

    private UserDetails buildUserDetails(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

