package com.dipartimento.demowebapplications.persistence.dao;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;

import java.util.List;

public interface PiattoDao {
    List<Piatto> findAll();
    Piatto findByPrimaryKey(String nome);
    void save(Piatto piatto);
    void delete(Piatto piatto);
    List<Piatto> findAllByRistoranteName(String name);
    Piatto findByID(int id);
    void create(Piatto piatto);
    void update(Piatto piatto);
    void deleteByID(int id);
    void addRistoranteToPiatto(int piattoId, int ristoranteId);
    void removeRistoranteFromPiatto(int piattoId, int ristoranteId);
}
