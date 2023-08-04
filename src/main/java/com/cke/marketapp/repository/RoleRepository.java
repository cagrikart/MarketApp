package com.cke.marketapp.repository;

import com.cke.marketapp.entities.ERole;
import com.cke.marketapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}