package com.ehousemanager.ehousemanager.dtos.companies;

import com.ehousemanager.ehousemanager.dtos.employees.EmployeeCompanyDto;

import java.util.List;
import java.util.UUID;

public record CompanyDto(UUID id, String name, List<EmployeeCompanyDto> employees) {
}
