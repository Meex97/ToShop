package com.project.Toshop.repository;

import com.project.Toshop.entity.ProductClient;
import com.project.Toshop.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductClientRepository extends JpaRepository<ProductClient, String> {

    ProductClient findByProductId(String id);

    Page<ProductClient> findAllByOrderByCreateTime(Pageable pageable);






}
