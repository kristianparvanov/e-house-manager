package com.ehousemanager.ehousemanager.dtos.employees;

import java.util.UUID;

public record EmployeeInfoDto(UUID id, String name, String email) {
}
