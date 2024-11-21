package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PiattoDaoProxy implements PiattoDao {

    private final PiattoDao piattoDao;
    private final Map<String, Piatto> cache;

    public PiattoDaoProxy(Connection conn) {
        this.piattoDao = new PiattoDaoJDBC(conn);
        this.cache = new HashMap<>();
    }

    @Override
    public List<Piatto> findAll() {
        return piattoDao.findAll();
    }

    @Override
    public Piatto findByPrimaryKey(String nome) {
        if (cache.containsKey(nome)) {
            return cache.get(nome);
        } else {
            Piatto piatto = piattoDao.findByPrimaryKey(nome);
            if (piatto != null)
                cache.put(nome, piatto);
            return piatto;
        }
    }

    @Override
    public void save(Piatto piatto) {
        piattoDao.save(piatto);
        cache.put(piatto.getNome(), piatto);
    }

    @Override
    public void delete(Piatto piatto) {
        piattoDao.delete(piatto);
        cache.remove(piatto.getNome());
    }

    @Override
    public List<Piatto> findAllByRistoranteName(String name) {
        return piattoDao.findAllByRistoranteName(name);
    }

    @Override
    public Piatto findByID(int id) {
        return null;
    }

    @Override
    public void create(Piatto piatto) {
        piattoDao.create(piatto);
    }

    @Override
    public void update(Piatto piatto) {
        piattoDao.update(piatto);
    }

    @Override
    public void deleteByID(int id) {
        piattoDao.deleteByID(id);
    }

    @Override
    public void addRistoranteToPiatto(int piattoId, int ristoranteId) {
        piattoDao.addRistoranteToPiatto(piattoId, ristoranteId);
    }

    @Override
    public void removeRistoranteFromPiatto(int piattoId, int ristoranteId) {
        piattoDao.removeRistoranteFromPiatto(piattoId, ristoranteId);
    }
}