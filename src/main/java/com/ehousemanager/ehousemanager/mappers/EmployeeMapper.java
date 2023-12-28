package com.ehousemanager.ehousemanager.mappers;

import com.ehousemanager.ehousemanager.dtos.employees.CreateEmployeeDto;
import com.ehousemanager.ehousemanager.dtos.employees.EmployeeCompanyDto;
import com.ehousemanager.ehousemanager.dtos.employees.EmployeeDto;
import com.ehousemanager.ehousemanager.dtos.employees.UpdateEmployeeDto;
import com.ehousemanager.ehousemanager.entities.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmployeeMapper {

    EmployeeCompanyDto toCompanyDto(Employee employee);

    @Mapping(target = "company", source = "employee.company.name")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "company", ignore = true)
    Employee toEntity(CreateEmployeeDto request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "buildings", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Employee update(UpdateEmployeeDto dto, @MappingTarget Employee employee);
}
