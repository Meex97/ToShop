package com.project.Toshop.repository;

import com.project.Toshop.entity.Client;
import com.project.Toshop.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
    Supplier findByEmail(String email);
}
