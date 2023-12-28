package com.ehousemanager.ehousemanager.controllers;

import com.ehousemanager.ehousemanager.dtos.employees.CreateEmployeeDto;
import com.ehousemanager.ehousemanager.dtos.employees.EmployeeDto;
import com.ehousemanager.ehousemanager.dtos.employees.UpdateEmployeeDto;
import com.ehousemanager.ehousemanager.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "Employee")
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create an employee by name, email and company ID", description = "Returns an employee.")
    @PostMapping("/create")
    public EmployeeDto create(@Valid @RequestBody CreateEmployeeDto request) {
        return employeeService.create(request);
    }

    @Operation(summary = "Get an employee by id", description = "Returns an employee information by id.")
    @GetMapping("/{id}")
    public EmployeeDto read(@PathVariable UUID id) {
        return employeeService.read(id);
    }

    @Operation(summary = "Get an employee by email", description = "Returns an employee information by email.")
    @GetMapping("/search")
    public EmployeeDto searchByEmail(@RequestParam @Email String email) {
        return employeeService.searchByEmail(email);
    }

    @Operation(summary = "Update an employee name by id", description = "Returns a employee.")
    @PostMapping("/{id}/update")
    public EmployeeDto update(@PathVariable UUID id,
                              @Valid @RequestBody UpdateEmployeeDto request) {
        return employeeService.update(id, request);
    }
}
