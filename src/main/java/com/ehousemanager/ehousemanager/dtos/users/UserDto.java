package com.ehousemanager.ehousemanager.dtos.users;

import java.util.UUID;

public record UserDto(UUID id, String name, Integer age, String email) {
}
