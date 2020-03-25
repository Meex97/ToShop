package com.project.Toshop.repository;

import com.project.Toshop.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;


public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /*static List<ProductInfo> findByIdUtente(Long idUtente) {
        List<ProductInfo> prodotti = this.findAll();
    }*/

    Collection<ProductInfo> findAllByIdUtente(Long idUtente);

    ProductInfo findByProductId(String id);
    //ProductInfo findByProductId(Long id);
    // onsale product
    Page<ProductInfo> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    // product in one category
    Page<ProductInfo> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<ProductInfo> findAllByOrderByProductId(Pageable pageable);

//    Page<ProductInfo> findByIdUtente(Long IdUtente)
    List<ProductInfo> findByIdUtente(Long idUtente);

    //Page<ProductInfo> findAllByOrderByProductIdByIdUtente(Pageable pageable, Long idUtente);

}
