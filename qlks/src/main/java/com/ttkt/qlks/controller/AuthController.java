package com.ttkt.qlks.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostMapping("/api/v1/eaut/login")
    public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest authRequest) {
        try {
            // Log thông tin đăng nhập
            System.out.println("Đang xác thực người dùng: " + authRequest.getUsername());

            // Authenticate using plain password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse("OK")); // Return JWT token in the response body
        } catch (AuthenticationException e) {
            // Log chi tiết lỗi xác thực
            e.printStackTrace();
            return ResponseEntity.status(401).body(new AuthResponse("Invalid username or password"));
        } catch (Exception e) {
            // Log chi tiết lỗi khác
            e.printStackTrace();
            return ResponseEntity.status(500).body(new AuthResponse("An error has occurred, please try again later."));
        }
    }
}

@Setter
@Getter
class AuthRequest {
    private String username;
    private String password;
}