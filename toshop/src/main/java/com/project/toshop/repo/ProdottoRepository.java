package com.project.toshop.repo;

import com.project.toshop.model.Prodotto;
import org.springframework.data.repository.CrudRepository;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long > {

    @Override
    void delete(Prodotto deleted);
}
