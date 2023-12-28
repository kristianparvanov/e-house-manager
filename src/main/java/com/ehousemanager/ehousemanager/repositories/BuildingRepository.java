package com.ehousemanager.ehousemanager.repositories;

import com.ehousemanager.ehousemanager.entities.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuildingRepository extends JpaRepository<Building, UUID> {

    boolean existsByNameIgnoreCaseAndAddressIgnoreCase(String name, String address);

    @Query(value = "SELECT *\n"
            + "FROM e_house_manager.buildings\n"
            + "WHERE UPPER(address) LIKE '%' || UPPER(:address) || '%' \n"
            + "ORDER BY address, name", nativeQuery = true)
    Page<Building> findByAddressLikeIgnoreCase(String address, Pageable pageable);
}
