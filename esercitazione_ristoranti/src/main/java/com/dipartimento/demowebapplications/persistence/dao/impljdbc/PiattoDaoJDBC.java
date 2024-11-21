package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.DBManager;
import com.dipartimento.demowebapplications.persistence.dao.PiattoDao;
import com.dipartimento.demowebapplications.persistence.dao.RistoranteDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDaoJDBC implements PiattoDao {
    Connection conn;

    public PiattoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Piatto> findAll() {
        List<Piatto> piatti = new ArrayList<Piatto>();
        String query = "select * from piatto";
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Piatto piatto = new Piatto();

                piatto.setNome(rs.getString("nome"));
                piatto.setIngredienti(rs.getString("ingredienti"));

                RistoranteDao ristoranteDao = DBManager.getInstance().getRistoranteDao();
                String key = rs.getString("idristorante");
                Ristorante ristorante = ristoranteDao.findByPrimaryKey(key);
                piatto.setRistorante(ristorante);

                piatti.add(piatto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return piatti;
    }

    @Override
    public Piatto findByPrimaryKey(String nome) {
        Piatto piatto = new Piatto();
        String query = "select * from piatto where nome = ?";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            piatto.setNome(rs.getString("nome"));
            piatto.setIngredienti(rs.getString("ingredienti"));

            RistoranteDao ristoranteDao = DBManager.getInstance().getRistoranteDao();
            String key = rs.getString("idristorante");
            Ristorante ristorante = ristoranteDao.findByPrimaryKey(key);
            piatto.setRistorante(ristorante);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return piatto;
    }

    @Override
    public void save(Piatto piatto) {
        String query = "insert into ristorante (nome, descrizione, ubicazione) values (?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, piatto.getIngredienti());
            st.setString(2, piatto.getRistorante().getNome());
            st.setString(3, piatto.getNome());
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Piatto piatto) {
        String query = "delete from piatto where nome = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, piatto.getNome());
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Piatto> findAllByRistoranteName(String name) {
        List<Piatto> piatti = new ArrayList<Piatto>();
        String query = "select * from piatto where idristorante = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Piatto piatto = new Piatto();

                piatto.setNome(rs.getString("nome"));
                piatto.setIngredienti(rs.getString("ingredienti"));

                RistoranteDao ristoranteDao = DBManager.getInstance().getRistoranteDao();
                String key = rs.getString("idristorante");
                Ristorante ristorante = ristoranteDao.findByPrimaryKey(key);
                piatto.setRistorante(ristorante);

                piatti.add(piatto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return piatti;
    }

    @Override
    public Piatto findByID(int id) {
        Piatto piatto = new Piatto();
        String query = "select * from piatto where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            piatto.setNome(rs.getString("nome"));
            piatto.setIngredienti(rs.getString("ingredienti"));

            RistoranteDao ristoranteDao = DBManager.getInstance().getRistoranteDao();
            String key = rs.getString("idristorante");
            Ristorante ristorante = ristoranteDao.findByPrimaryKey(key);
            piatto.setRistorante(ristorante);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return piatto;
    }

    @Override
    public void create(Piatto piatto) {
        String query = "insert into piatto (nome, ingredienti) values (?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, piatto.getNome());
            st.setString(2, piatto.getIngredienti());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Piatto piatto) {
        String query = "update piatto set ingredienti = ? where nome = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, piatto.getIngredienti());
            st.setString(2, piatto.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(int id) {
        String query = "delete from piatto where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addRistoranteToPiatto(int piattoId, int ristoranteId) {
        String query = "insert into piatto_ristorante (piatto, ristorante) values (?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, piattoId);
            st.setInt(2, ristoranteId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeRistoranteFromPiatto(int piattoId, int ristoranteId) {

    }

    public static void main(String[] args) {
        PiattoDao piattoDao = DBManager.getInstance().getPiattoDao();
        List<Piatto> piatti = piattoDao.findAll();
        for (Piatto piatto : piatti) {
            System.out.println(piatto.getNome());
            System.out.println(piatto.getIngredienti());
        }
    }
}
