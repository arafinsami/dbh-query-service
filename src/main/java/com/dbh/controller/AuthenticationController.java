package com.dbh.controller;

import com.dbh.dto.LoginDTO;
import com.dbh.dto.LoginResponseDTO;
import com.dbh.dto.SingUpDTO;
import com.dbh.entity.Employee;
import com.dbh.service.AuthService;
import com.dbh.service.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Employee Authentication API")
@RequestMapping(path = "authentication")
public class AuthenticationController {

    private final AuthService authService;

    private final JwtTokenService tokenService;

    @PostMapping("signup")
    @Operation(summary = "signup an employee")
    public ResponseEntity<Employee> signUp(@RequestBody SingUpDTO singUpDTO) {
        Employee employee = authService.signup(singUpDTO);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("login")
    @Operation(summary = "login an employee")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Employee employee = authService.login(loginDTO);
        String token = tokenService.generateToken(employee);
        LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                .token(token)
                .expiresIn(tokenService.getExpirationTime())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
