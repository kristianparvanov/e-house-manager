package com.ehousemanager.ehousemanager.controllers;

import com.ehousemanager.ehousemanager.dtos.buildings.BuildingDto;
import com.ehousemanager.ehousemanager.dtos.buildings.CreateBuildingDto;
import com.ehousemanager.ehousemanager.services.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "Building")
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @Operation(summary = "Create a building by name", description = "Returns a building.")
    @PostMapping("/create")
    public BuildingDto create(@Valid @RequestBody CreateBuildingDto request) {
        return buildingService.create(request);
    }

    @Operation(summary = "Get all buildings", description = "Returns a page of buildings.")
    @GetMapping
    public Page<BuildingDto> readAll(Pageable pageable) {
        return buildingService.readAll(pageable);
    }

    @Operation(summary = "Get a building by id", description = "Returns a building information by id.")
    @GetMapping("/{id}")
    public BuildingDto read(@PathVariable UUID id) {
        return buildingService.read(id);
    }

    @Operation(summary = "Get buildings by address like", description = "Returns a page of buildings")
    @GetMapping("/search")
    public Page<BuildingDto> searchByAddressLike(@RequestParam String address, Pageable pageable) {
        return buildingService.searchByAddressLike(address, pageable);
    }

    @Operation(summary = "Update a building", description = "Returns a building.")
    @PostMapping("/{id}/update")
    public BuildingDto update(@PathVariable UUID id,
                              @Valid @RequestBody CreateBuildingDto request) {
        return buildingService.update(id, request);
    }
}
