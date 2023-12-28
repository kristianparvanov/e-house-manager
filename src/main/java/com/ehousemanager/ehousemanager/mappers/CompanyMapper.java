package com.ehousemanager.ehousemanager.mappers;

import com.ehousemanager.ehousemanager.dtos.companies.CompanyAllDto;
import com.ehousemanager.ehousemanager.dtos.companies.CompanyDto;
import com.ehousemanager.ehousemanager.dtos.companies.CreateCompanyDto;
import com.ehousemanager.ehousemanager.dtos.companies.UpdateCompanyDto;
import com.ehousemanager.ehousemanager.entities.Company;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = EmployeeMapper.class)
public interface CompanyMapper {

    Company toEntity(CreateCompanyDto dto);

    CompanyDto toDto(Company company);

    CompanyAllDto toAllDto(Company company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Company update(UpdateCompanyDto dto, @MappingTarget Company company);
}
