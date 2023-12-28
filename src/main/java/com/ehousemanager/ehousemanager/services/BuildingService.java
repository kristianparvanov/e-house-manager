package com.ehousemanager.ehousemanager.services;

import com.ehousemanager.ehousemanager.dtos.buildings.BuildingDto;
import com.ehousemanager.ehousemanager.dtos.buildings.CreateBuildingDto;
import com.ehousemanager.ehousemanager.entities.Building;
import com.ehousemanager.ehousemanager.exceptions.BuildingException;
import com.ehousemanager.ehousemanager.mappers.BuildingMapper;
import com.ehousemanager.ehousemanager.repositories.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private static final String BUILDING_ALREADY_EXISTS_WITH_NAME_OR_ADDRESS = "Building already exists - "
            + "name: %s, address: %s";
    private static final String BUILDING_NOT_FOUND_ID_TEMPLATE = "Building not found - id: %s";
    private static final String BUILDING_NOT_FOUND_NAME_TEMPLATE = "Building not found - name: %s";

    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    public BuildingDto create(CreateBuildingDto request) {
        validateBuilding(request.name(), request.address());

        return buildingMapper.toDto(buildingRepository.save(buildingMapper.toEntity(request)));
    }

    public Page<BuildingDto> readAll(Pageable pageable) {
        // TODO
        return null;
    }

    public BuildingDto read(UUID id) {
        return buildingMapper.toDto(getById(id));
    }

    public Page<BuildingDto> searchByAddressLike(String address, Pageable pageable) {
        return buildingRepository.findByAddressLikeIgnoreCase(address, pageable)
                .map(buildingMapper::toDto);
    }

    public BuildingDto update(UUID id, CreateBuildingDto request) {
        Building building = getById(id);
        String newName = request.name();
        String newAddress = request.address();

        if (!newName.equalsIgnoreCase(building.getName()) || !newAddress.equalsIgnoreCase(building.getAddress())) {
            validateBuilding(newName, newAddress);
        }

        return buildingMapper.toDto(buildingRepository.save(buildingMapper.update(request, building)));
    }

    private Building getById(UUID id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new BuildingException(String.format(BUILDING_NOT_FOUND_ID_TEMPLATE, id)));
    }

    private void validateBuilding(String name, String address) {
        if (buildingRepository.existsByNameIgnoreCaseAndAddressIgnoreCase(name, address)) {
            throw new BuildingException(String.format(BUILDING_ALREADY_EXISTS_WITH_NAME_OR_ADDRESS, name, address));
        }
    }
}
