package unito.progetto.esame.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import unito.progetto.esame.model.ProductClient;

public interface ProductClientRepository extends JpaRepository<ProductClient, String> {

    ProductClient findByProductId(String id);

    Page<ProductClient> findAllByOrderByCreateTime(Pageable pageable);








}
