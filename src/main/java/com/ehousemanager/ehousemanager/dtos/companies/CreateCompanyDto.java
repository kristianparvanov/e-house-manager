package com.ehousemanager.ehousemanager.dtos.companies;

import jakarta.validation.constraints.NotBlank;

public record CreateCompanyDto(@NotBlank String name) {
}
