package com.ehousemanager.ehousemanager.dtos.employees;

import java.util.UUID;

public record EmployeeDto(UUID id, String name, String company) {
}
