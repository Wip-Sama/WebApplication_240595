package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.dao.RistoranteDao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RistoranteDaoProxy implements RistoranteDao {

    private final RistoranteDao ristoranteDao;
    private final Map<String, Ristorante> cache_ristoranti;
    private final Map<String, Piatto> cache_piatti;

    public RistoranteDaoProxy(Connection conn) {
        this.ristoranteDao = new RistoranteDaoJDBC(conn);
        this.cache_ristoranti = new HashMap<>();
        this.cache_piatti = new HashMap<>();
    }

    @Override
    public Ristorante findByPrimaryKey(String nome) {
        return null;
    }

    @Override
    public void save(Ristorante ristorante) {
        ristoranteDao.save(ristorante);
    }

    @Override
    public void delete(Ristorante ristorante) {
        ristoranteDao.delete(ristorante);
    }

    @Override
    public void addPiattoToRistorante(int ristoranteId, int piattoId) {
        ristoranteDao.addPiattoToRistorante(ristoranteId, piattoId);
    }

    @Override
    public void removePiattoFromRistorante(int ristoranteId, int piattoId) {
        ristoranteDao.removePiattoFromRistorante(ristoranteId, piattoId);
    }

    @Override
    public List<Piatto> findPiattiByRistorante(int ristoranteId) {
        List<Piatto> piatti = ristoranteDao.findPiattiByRistorante(ristoranteId);
        for (Piatto piatto : piatti) {
            cache_piatti.put(piatto.getNome(), piatto);
        }
        return piatti;
    }

    @Override
    public List<Ristorante> findAll() {
        List<Ristorante> ristoranti = ristoranteDao.findAll();
        for (Ristorante ristorante : ristoranti) {
            cache_ristoranti.put(ristorante.getNome(), ristorante);
        }
        return ristoranti;
    }
}