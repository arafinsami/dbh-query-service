package com.dbh.service;

import com.dbh.dto.LoginDTO;
import com.dbh.dto.SingUpDTO;
import com.dbh.entity.Employee;
import com.dbh.exception.ResourceNotFoundException;
import com.dbh.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public Employee signup(SingUpDTO singUpDTO) {
        Employee employee = Employee.builder()
                .name(singUpDTO.getName())
                .email(singUpDTO.getEmail())
                .password(passwordEncoder.encode(singUpDTO.getPassword()))
                .build();
        return employeeRepository.save(employee);
    }

    public Employee login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        return employeeRepository.findByEmail(loginDTO.getEmail()).orElseThrow(
                ()-> new ResourceNotFoundException("user email not foud !!!")
        );
    }
}
