package com.ehousemanager.ehousemanager.dtos.employees;

import com.ehousemanager.ehousemanager.dtos.buildings.BuildingInfoDto;

import java.util.List;
import java.util.UUID;

public record EmployeeDto(UUID id, String name, String email, String company, List<BuildingInfoDto> buildings) {
}
