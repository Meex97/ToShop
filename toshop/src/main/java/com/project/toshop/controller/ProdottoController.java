package com.project.toshop.controller;

import com.project.toshop.model.Prodotto;
import com.project.toshop.model.Prodotto_fornitore;
import com.project.toshop.model.Prodotto_valutato;
import com.project.toshop.model.User;
import com.project.toshop.repo.ProdottoRepository;
import com.project.toshop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProdottoController {


    @Autowired
    ProdottoRepository repository;

    @GetMapping("/prodotto")
    public List<Prodotto> getAllProdotti(){

        List<Prodotto> prodotti = new ArrayList<>();
        repository.findAll().forEach(prodotti::add);

        return prodotti;
    }


    //Creazioni
    @PostMapping(value = "/prodotto/createProdotto")
    public Prodotto postCreateProdotto(@RequestBody Prodotto prodotto){
        Prodotto _prodotto = repository.save(new Prodotto(prodotto.getNome(), prodotto.getDescrizione(),prodotto.getPrezzo(),prodotto.getCategoria()));
        return _prodotto;
    }

    //Creazioni
    @PostMapping(value = "/prodotto/createProdottoFornitore")
    public Prodotto_fornitore postCreateProdottoFornitore(@RequestBody Prodotto_fornitore prodotto_fornitore){
        Prodotto_fornitore _prodotto_fornitore = repository.save(new Prodotto_fornitore(prodotto_fornitore.getNome(), prodotto_fornitore.getDescrizione(),prodotto_fornitore.getPrezzo(),prodotto_fornitore.getCategoria(),prodotto_fornitore.getNr_pezzi()));
        return _prodotto_fornitore;
    }


    //Creazioni
    @PostMapping(value = "/prodotto/createProdottoValutato")
    public Prodotto_valutato postCreateProdottoValutato(@RequestBody Prodotto_valutato prodotto_valutato){
        Prodotto_valutato _prodotto_valutato = repository.save(new Prodotto_valutato(prodotto_valutato.getNome(), prodotto_valutato.getDescrizione(),prodotto_valutato.getPrezzo(),prodotto_valutato.getCategoria(),prodotto_valutato.getCodice(),prodotto_valutato.getPrezzo_proposto(),prodotto_valutato.isValutabile(),prodotto_valutato.isValutato()));
        return _prodotto_valutato;
    }


}
