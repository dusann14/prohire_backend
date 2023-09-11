package com.example.demo.controller;

import com.example.demo.model.domain.AppUser;
import com.example.demo.model.domain.ConfirmationToken;
import com.example.demo.model.domainDTO.AppUserDTO;
import com.example.demo.model.domainDTO.ConfirmationTokenDTO;
import com.example.demo.model.domainDTO.EmployerDTO;
import com.example.demo.model.request_response.*;
import com.example.demo.service.AuthenticationService;
import io.jsonwebtoken.JwtException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AppUserDTO> register(@RequestBody AppUserDTO user) throws MessagingException {
        return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AppUserDTO> authenticate(@RequestBody AppUser appUser){
        return ResponseEntity.ok(service.authenticate(appUser));
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<ConfirmationTokenDTO> confirmToken(@RequestParam("token") String token) {
        return ResponseEntity.ok(service.confirmToken(token));
    }

    @PutMapping
    public ResponseEntity<AppUserDTO> updateUser(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(service.updateUser(request));
    }



}