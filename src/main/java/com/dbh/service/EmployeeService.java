package com.dbh.service;

import com.dbh.entity.Employee;
import com.dbh.exception.ResourceNotFoundException;
import com.dbh.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findByEmployeeId(Long id) {
        return employeeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
