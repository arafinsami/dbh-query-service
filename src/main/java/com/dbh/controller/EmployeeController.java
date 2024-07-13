package com.dbh.controller;

import com.dbh.dto.EmployeeResponseDTO;
import com.dbh.entity.Employee;
import com.dbh.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Employee Query API")
@RequestMapping(path = "dbh-employee-query")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Employee> employees = employeeService.findAll();
        List<EmployeeResponseDTO> responseDTOS = employees.stream()
                .map(employee -> EmployeeResponseDTO.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .email(employee.getEmail())
                        .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "find an employee by id")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Long id) {
        Employee employee = employeeService.findByEmployeeId(id);
        EmployeeResponseDTO responseDTO = EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
