package com.unito.toshop_back_v3.repository;

import java.util.Optional;

import com.unito.toshop_back_v3.models.ERole;
import com.unito.toshop_back_v3.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
