package com.ehousemanager.ehousemanager.controllers;

import com.ehousemanager.ehousemanager.dtos.companies.CompanyAllDto;
import com.ehousemanager.ehousemanager.dtos.companies.CompanyDto;
import com.ehousemanager.ehousemanager.dtos.companies.CreateCompanyDto;
import com.ehousemanager.ehousemanager.dtos.companies.UpdateCompanyDto;
import com.ehousemanager.ehousemanager.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
@Tag(name = "Company")
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "Create a company by name", description = "Returns a company.")
    @PostMapping("/create")
    public CompanyDto create(@Valid @RequestBody CreateCompanyDto request) {
        return companyService.create(request);
    }

    @Operation(summary = "Get all companies", description = "Returns a page of companies.")
    @GetMapping
    public Page<CompanyAllDto> readAll(Pageable pageable) {
        return companyService.readAll(pageable);
    }

    @Operation(summary = "Get a company by id", description = "Returns a company information by id.")
    @GetMapping("/{id}")
    public CompanyDto read(@PathVariable UUID id) {
        return companyService.read(id);
    }

    @Operation(summary = "Get a company by name", description = "Returns a company information by name.")
    @GetMapping("/search")
    public CompanyDto search(@RequestParam @NotBlank String name) {
        return companyService.searchByName(name);
    }

    @Operation(summary = "Update a company name by id", description = "Returns a company.")
    @PostMapping("/{id}/update")
    public CompanyDto update(@PathVariable UUID id,
                             @Valid @RequestBody UpdateCompanyDto request) {
        return companyService.update(id, request);
    }
}
