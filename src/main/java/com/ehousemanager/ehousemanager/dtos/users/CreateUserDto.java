package com.ehousemanager.ehousemanager.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotBlank String name,
        @Email String email,
        @NotNull @Min(1) Integer age
) {
}
