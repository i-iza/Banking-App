package com.DummyBank.BankingApplication.controller;

import com.DummyBank.BankingApplication.security.JwtUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Attempting login for username: " + loginRequest.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Authentication successful for username: " + loginRequest.getUsername());
            System.out.println("Authorities: " + authentication.getAuthorities());

            String role = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
            String token = jwtUtils.generateToken(loginRequest.getUsername(), role);

            System.out.println("Generated JWT token for user: " + loginRequest.getUsername());

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: Bad credentials for username: " + loginRequest.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password");
        } catch (Exception e) {
            System.out.println("Authentication error for username: " + loginRequest.getUsername());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    public static class JwtResponse {
        private final String token;
    }
}
