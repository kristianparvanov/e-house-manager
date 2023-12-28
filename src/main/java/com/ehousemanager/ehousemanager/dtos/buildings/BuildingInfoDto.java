package com.ehousemanager.ehousemanager.dtos.buildings;

import java.util.UUID;

public record BuildingInfoDto(UUID id, String name, String address) {
}
