package com.ehousemanager.ehousemanager.mappers;

import com.ehousemanager.ehousemanager.dtos.users.CreateUserDto;
import com.ehousemanager.ehousemanager.dtos.users.UserDto;
import com.ehousemanager.ehousemanager.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    User toEntity(CreateUserDto dto);

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apartments", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    User update(CreateUserDto dto, @MappingTarget User user);
}
