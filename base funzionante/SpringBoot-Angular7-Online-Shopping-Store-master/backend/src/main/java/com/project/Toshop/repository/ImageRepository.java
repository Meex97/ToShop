package com.project.Toshop.repository;

import com.project.Toshop.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}
