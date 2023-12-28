package com.ehousemanager.ehousemanager.dtos.buildings;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBuildingDto(
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotNull
        @Min(1)
        Integer floorCount,
        @NotNull
        @Min(1)
        Integer apartmentCount,
        @NotNull
        @Min(1)
        Double buildUpArea,
        @NotNull
        @Min(1)
        Double commonArea
) {
}
