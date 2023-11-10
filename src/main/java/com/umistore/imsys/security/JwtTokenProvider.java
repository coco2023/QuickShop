package com.umistore.imsys.security;

import com.umistore.imsys.constant.SecurityConstants;
import com.umistore.imsys.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * JwtTokenProvider is only needed for creating tokens and not for loading user details
 */
@Component
@Log4j2
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}") // 1h by default
    private long validityInMilliseconds;

    // Initialization block that encodes the secretKey
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

//    public String getUsername(String token) {
//        // Extract the username from the token
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//    }

//    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
//        Claims claims = Jwts.claims().setSubject(username);
//        claims.put("auth", authorities.stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//
//        return SecurityConstants.TOKEN_PREFIX + token;
//    }
//
//    public String createToken(String username, List<String> roles, List<String> permissions) {
//        Claims claims = Jwts.claims().setSubject(username);
//        claims.put("roles", roles);
//        claims.put("permissions", permissions);
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        log.info("***claims: " + claims);

        List<String> roles = claims.get("roles", List.class);
        List<String> permissions = claims.get("permissions", List.class);

        log.info("***roles: " + roles);
        log.info("***permissions: " + permissions);

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
//            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }
        if (permissions != null) {
            permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
        }

        User principal = new User(username, "", authorities);
        UsernamePasswordAuthenticationToken res = new UsernamePasswordAuthenticationToken(principal, token, authorities);
        log.info("***UsernamePasswordAuthenticationToken: " + res);
        return res;

//        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }


//    public Authentication getAuthentication(String token) {
//        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        String username = claims.getSubject();
//        Collection<? extends GrantedAuthority> authorities =
//                ((List<?>) claims.get("auth")).stream()
//                        .map(authority -> new SimpleGrantedAuthority((String) authority))
//                        .collect(Collectors.toList());
//
//        return new UsernamePasswordAuthenticationToken(username, token, authorities);
//    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            log.info("***Valid Token!!!***");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Here you should implement logging and possibly throw an exception depending on your application's needs
            throw new JwtAuthenticationException("Expired or invalid JWT token");
        }
    }

    public String createToken(Authentication authentication) {
        String username = authentication.getName();
        log.info("username: " + username);
        // Collecting roles and permissions from authentication authorities
        List<String> roles = authentication.getAuthorities().stream()
                .filter(auth -> auth.getAuthority().startsWith("ROLE_"))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        log.info("***roles: " + roles);
        List<String> permissions = authentication.getAuthorities().stream()
                .filter(auth -> !auth.getAuthority().startsWith("ROLE_"))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        log.info("***permissions: " + permissions);

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        claims.put("permissions", permissions);
        log.info("***Clains: " + claims);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return SecurityConstants.TOKEN_PREFIX + token;
    }
}
