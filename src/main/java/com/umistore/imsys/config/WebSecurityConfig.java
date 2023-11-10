package com.umistore.imsys.config;

import com.umistore.imsys.constant.SecurityConstants;
import com.umistore.imsys.security.JwtAuthenticationEntryPoint;
import com.umistore.imsys.security.JwtTokenFilter;
import com.umistore.imsys.security.JwtTokenProvider;
import com.umistore.imsys.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                // Permit all users to access the home page and other static resources
                .antMatchers("/home", "/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers(SecurityConstants.SWAGGER_WHITELIST).permitAll()
                .antMatchers(SecurityConstants.H2_CONSOLE).permitAll()
//                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/v2/api-docs/**").permitAll()
                .antMatchers("/error").permitAll()

                // Allow everyone to access the login endpoint
                .antMatchers("/api/auth/login", "/api/auth/register").permitAll()

                // Any other requests must be authenticated
                .antMatchers(HttpMethod.GET, "/api/test/customer").hasAnyRole("CUSTOMER", "MANAGER")
                .antMatchers(HttpMethod.GET, "/api/test/manager").hasAnyRole("MANAGER")
                .antMatchers(HttpMethod.POST, "/api/test/manager").hasAnyRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/skus/all").hasAuthority("CREATE_PRODUCTS")
                .antMatchers(HttpMethod.GET, "/api/orders/all").hasAuthority("CREATE_PRODUCTS")
                .antMatchers(HttpMethod.GET, "/api/products/all").hasAuthority("READ_PRODUCTS")
                .antMatchers(HttpMethod.POST, "/api/products/create").hasAuthority("CREATE_PRODUCTS")

                .antMatchers("/api/**").hasRole("MANAGER")
//                .antMatchers("/api/**").hasRole("ADMIN")


                .anyRequest().authenticated() // All other endpoints require authentication

                .and()
//                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                // In-memory authentication for demonstration purposes
//                .inMemoryAuthentication()
//                .withUser("customer").password(passwordEncoder().encode("password")).roles("CUSTOMER")
//                .and()
//                .withUser("manager").password(passwordEncoder().encode("password")).roles("MANAGER");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // A secure password encoder is defined as a bean to be used across the application
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    // Optional: For enabling CORS
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*")); // Allow appropriate domains
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        // Include the /api context path in the CORS configuration
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    public void configure(WebSecurity web) {
        web.debug(true);
    }

}
