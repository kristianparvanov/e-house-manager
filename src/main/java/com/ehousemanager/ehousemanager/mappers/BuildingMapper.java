package com.ehousemanager.ehousemanager.mappers;

import com.ehousemanager.ehousemanager.dtos.buildings.BuildingDto;
import com.ehousemanager.ehousemanager.dtos.buildings.BuildingInfoDto;
import com.ehousemanager.ehousemanager.dtos.buildings.CreateBuildingDto;
import com.ehousemanager.ehousemanager.entities.Building;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BuildingMapper {

    Building toEntity(CreateBuildingDto dto);

    @Mapping(target = "employeeEmail", source = "building.employee.email")
    BuildingDto toDto(Building building);

    BuildingInfoDto toInfoDto(Building building);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "apartments", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Building update(CreateBuildingDto dto, @MappingTarget Building building);
}
