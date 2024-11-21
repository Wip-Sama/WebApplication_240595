package com.dipartimento.demowebapplications.persistence.dao;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;

import java.util.List;

public interface RistoranteDao {
    List<Ristorante> findAll();
    Ristorante findByPrimaryKey(String nome);
    void save(Ristorante ristorante);
    void delete(Ristorante ristorante);
    void addPiattoToRistorante(int ristoranteId, int piattoId);
    void removePiattoFromRistorante(int ristoranteId, int piattoId);
    List<Piatto> findPiattiByRistorante(int ristoranteId);
}

