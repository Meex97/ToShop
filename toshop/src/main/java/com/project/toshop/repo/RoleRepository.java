package com.project.toshop.repo;

import com.project.toshop.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long > {

    Role findByRole(String role);
}
