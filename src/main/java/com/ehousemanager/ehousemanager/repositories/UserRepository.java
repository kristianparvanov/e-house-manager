package com.ehousemanager.ehousemanager.repositories;

import com.ehousemanager.ehousemanager.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT *\n"
            + "FROM e_house_manager.users\n"
            + "WHERE UPPER(name) LIKE '%' || UPPER(:name) || '%'\n"
            + "ORDER BY name", nativeQuery = true)
    Page<User> findByNameLikeIgnoreCaseOrderByName(String name, Pageable pageable);

    Page<User> findAllByOrderByName(Pageable pageable);
}
