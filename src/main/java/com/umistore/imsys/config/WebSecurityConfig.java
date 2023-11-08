package com.umistore.imsys.config;

import com.umistore.imsys.constant.SecurityConstants;
import com.umistore.imsys.security.JwtAuthenticationEntryPoint;
import com.umistore.imsys.security.JwtTokenFilter;
import com.umistore.imsys.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // Permit all users to access the home page and other static resources
                .antMatchers("/", "/home", "/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers(SecurityConstants.SWAGGER_WHITELIST).permitAll()
                .antMatchers(SecurityConstants.H2_CONSOLE).permitAll()

                // Allow everyone to access the login endpoint
                .antMatchers("/auth/login").permitAll()
                // Manager role can access any API under /api
                .antMatchers("/**").hasRole("MANAGER")

                // Allow customer role to search for products and get all products
                .antMatchers("/products/search", "/products/all").hasRole("CUSTOMER")
                .anyRequest().authenticated() // All other endpoints require authentication
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // In-memory authentication for demonstration purposes
                .inMemoryAuthentication()
                .withUser("customer").password(passwordEncoder().encode("password")).roles("CUSTOMER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("password")).roles("MANAGER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // A secure password encoder is defined as a bean to be used across the application
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
