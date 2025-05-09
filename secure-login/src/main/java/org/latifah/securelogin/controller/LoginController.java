package org.latifah.securelogin.controller;

import org.latifah.securelogin.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private JwtUtil jwtUtil = new JwtUtil();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        // For demo: hardcoded, use userDetailsService otherwise
        if (username.equals("admin") && password.equals("admin123")) {
            return ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(username, "ADMIN")));
        } else if (username.equals("client") && password.equals("client123")) {
            return ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(username, "CLIENT")));
        } else if (username.equals("employee") && password.equals("employee123")) {
            return ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(username, "EMPLOYEE")));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin";
    }

    @GetMapping("/client/dashboard")
    public String clientDashboard() {
        return "Welcome Client";
    }

    @GetMapping("/employee/dashboard")
    public String employeeDashboard() {
        return "Welcome Employee";
    }
}


