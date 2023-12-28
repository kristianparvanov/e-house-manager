package com.ehousemanager.ehousemanager.dtos.employees;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateEmployeeDto(@NotBlank String name, @Email String email, @NotNull UUID companyId) {
}
