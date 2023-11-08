//package com.umistore.imsys.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // Enable method-level security
//public class WebSecurityConfig1 extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                // CSRF token is typically needed for state-changing requests like POST.
//                // For REST APIs, it's common to disable CSRF protection.
//                .csrf().disable()
//                .authorizeRequests()
//                // Permit all users to access the home page and other static resources
//                .antMatchers("/", "/home", "/css/**", "/js/**", "/images/**").permitAll()
//                // Allow customer role to search for products and get all products
//                .antMatchers("/api/products/search", "/api/products").hasRole("CUSTOMER")
//                // Manager role can access any API under /api
//                .antMatchers("/api/**").hasRole("MANAGER")
//                // All other requests need to be authenticated
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                // Specify the login page (if you have a custom login page)
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                // In-memory authentication for demonstration purposes
//                .inMemoryAuthentication()
//                .withUser("customerUser").password(passwordEncoder().encode("password")).roles("CUSTOMER")
//                .and()
//                .withUser("managerUser").password(passwordEncoder().encode("password")).roles("MANAGER");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // A secure password encoder is defined as a bean to be used across the application
//        return new BCryptPasswordEncoder();
//    }
//}
