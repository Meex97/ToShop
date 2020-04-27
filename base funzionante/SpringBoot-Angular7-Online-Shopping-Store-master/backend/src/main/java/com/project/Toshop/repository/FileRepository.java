package com.project.Toshop.repository;

import com.project.Toshop.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {
}
