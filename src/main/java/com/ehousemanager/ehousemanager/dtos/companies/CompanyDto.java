package com.ehousemanager.ehousemanager.dtos.companies;

import com.ehousemanager.ehousemanager.dtos.employees.EmployeeInfoDto;

import java.util.List;
import java.util.UUID;

public record CompanyDto(UUID id, String name, List<EmployeeInfoDto> employees) {
}
