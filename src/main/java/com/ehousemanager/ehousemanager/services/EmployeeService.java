package com.ehousemanager.ehousemanager.services;

import com.ehousemanager.ehousemanager.dtos.employees.CreateEmployeeDto;
import com.ehousemanager.ehousemanager.dtos.employees.EmployeeDto;
import com.ehousemanager.ehousemanager.dtos.employees.UpdateEmployeeDto;
import com.ehousemanager.ehousemanager.entities.Company;
import com.ehousemanager.ehousemanager.entities.Employee;
import com.ehousemanager.ehousemanager.exceptions.EmployeeException;
import com.ehousemanager.ehousemanager.mappers.EmployeeMapper;
import com.ehousemanager.ehousemanager.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final String EMPLOYEE_NOT_FOUND_BY_ID_TEMPLATE = "Employee not found - id: %s";
    private static final String EMPLOYEE_NOT_FOUND_BY_EMAIL_TEMPLATE = "Employee not found - email: %s";
    private static final String EMPLOYEE_ALREADY_EXISTS = "Employee already exists - email: %s";

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final CompanyService companyService;

    public EmployeeDto create(CreateEmployeeDto request) {
        validateEmployee(request.email());

        Company company = companyService.getCompany(request.companyId());

        Employee employee = employeeMapper.toEntity(request);
        employee.setCompany(company);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeDto read(UUID id) {
        return employeeMapper.toDto(getById(id));
    }

    public EmployeeDto searchByEmail(String email) {
        return employeeRepository.findByEmailIgnoreCase(email)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeException(String.format(EMPLOYEE_NOT_FOUND_BY_EMAIL_TEMPLATE, email)));
    }

    public EmployeeDto update(UUID id, UpdateEmployeeDto request) {
        Employee employee = getById(id);

        validateEmployee(request.email());

        return employeeMapper.toDto(employeeRepository.save(employeeMapper.update(request, employee)));
    }

    private void validateEmployee(String email) {
        if (employeeRepository.existsByEmailIgnoreCase(email)) {
            throw new EmployeeException(String.format(EMPLOYEE_ALREADY_EXISTS, email));
        }
    }

    private Employee getById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException(String.format(EMPLOYEE_NOT_FOUND_BY_ID_TEMPLATE, id)));
    }
}
