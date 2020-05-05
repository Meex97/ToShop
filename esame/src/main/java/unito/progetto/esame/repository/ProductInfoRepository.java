package unito.progetto.esame.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import unito.progetto.esame.model.ProductInfo;

import java.util.Collection;
import java.util.List;


public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {



    Collection<ProductInfo> findAllByIdUtente(Long idUtente);

    ProductInfo findByProductId(String id);

    // onsale product
    Page<ProductInfo> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    // product in one category
    Page<ProductInfo> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<ProductInfo> findAllByOrderByProductId(Pageable pageable);


    List<ProductInfo> findByIdUtente(Long idUtente);



}
