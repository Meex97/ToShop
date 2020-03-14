package com.project.Toshop.repository;

import com.project.Toshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Zhu Lin on 1/2/2019.
 */

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
