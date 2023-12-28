package com.ehousemanager.ehousemanager.repositories;

import com.ehousemanager.ehousemanager.entities.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Company> findByNameIgnoreCase(String name);

    Page<Company> findAllByOrderByName(Pageable pageable);
}