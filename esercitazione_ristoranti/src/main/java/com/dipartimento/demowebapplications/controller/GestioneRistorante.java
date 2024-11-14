package com.dipartimento.demowebapplications.controller;

import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.DBManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestioneRistorante {
    @PostMapping("/addRistorante")
    public String aggiungiRistorante(@RequestBody Ristorante ristorante) {
        System.out.println("ristorante: " + ristorante.getNome());

        DBManager.getInstance().getRistoranteDao().save(ristorante);

        return "200";
    }
}
