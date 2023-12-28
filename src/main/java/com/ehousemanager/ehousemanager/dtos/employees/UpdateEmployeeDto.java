package com.ehousemanager.ehousemanager.dtos.employees;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateEmployeeDto(@NotBlank String name, @Email String email) {
}
