package com.ehousemanager.ehousemanager.services;

import com.ehousemanager.ehousemanager.dtos.companies.CompanyAllDto;
import com.ehousemanager.ehousemanager.dtos.companies.CompanyDto;
import com.ehousemanager.ehousemanager.dtos.companies.CreateCompanyDto;
import com.ehousemanager.ehousemanager.dtos.companies.UpdateCompanyDto;
import com.ehousemanager.ehousemanager.entities.Company;
import com.ehousemanager.ehousemanager.exceptions.CompanyException;
import com.ehousemanager.ehousemanager.mappers.CompanyMapper;
import com.ehousemanager.ehousemanager.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private static final String COMPANY_NOT_FOUND_BY_NAME_TEMPLATE = "Company not found, name: %s";
    private static final String COMPANY_NOT_FOUND_BY_ID_TEMPLATE = "Company not found, id: %s";
    private static final String COMPANY_ALREADY_EXISTS_BY_NAME_TEMPLATE = "Company already exists, name: %s";

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyDto create(CreateCompanyDto request) {
        validateName(request.name());

        return companyMapper.toDto(companyRepository.save(companyMapper.toEntity(request)));
    }

    public Page<CompanyAllDto> readAll(Pageable pageable) {
        return companyRepository.findAllByOrderByName(pageable)
                .map(companyMapper::toAllDto);
    }

    public CompanyDto read(UUID id) {
        return companyMapper.toDto(getCompany(id));
    }

    public CompanyDto read(String name) {
        return companyRepository.findByNameIgnoreCase(name)
                .map(companyMapper::toDto)
                .orElseThrow(() -> new CompanyException(String.format(COMPANY_NOT_FOUND_BY_NAME_TEMPLATE, name)));
    }

    public CompanyDto update(UUID id, UpdateCompanyDto request) {
        Company company = getCompany(id);

        validateName(request.name());

        return companyMapper.toDto(companyRepository.save(companyMapper.update(request, company)));
    }

    public Company getCompany(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyException(String.format(COMPANY_NOT_FOUND_BY_ID_TEMPLATE, id)));
    }

    private void validateName(String name) {
        if (companyRepository.existsByNameIgnoreCase(name)) {
            throw new CompanyException(String.format(COMPANY_ALREADY_EXISTS_BY_NAME_TEMPLATE, name));
        }
    }
}
