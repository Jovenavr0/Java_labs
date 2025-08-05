package ru.Jovenavr0.lab_4.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "Controller for work with auth")
@RequiredArgsConstructor
public class AuthController {

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Parameter(description = "Login") @RequestParam String username,
            @Parameter(description = "Password") @RequestParam String password
    ) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Logout user")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }

}
