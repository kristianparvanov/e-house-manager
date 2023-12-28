package com.ehousemanager.ehousemanager.dtos.buildings;

import java.util.UUID;

public record BuildingDto(UUID id, String name, String address, Integer floorCount,
                          Integer apartmentCount, Double buildUpArea, Double commonArea, String employeeEmail) {
}
