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

    public static void main(String[] args) {
        PiattoDao piattoDao = DBManager.getInstance().getPiattoDao();
        List<Piatto> piatti = piattoDao.findAll();
        for (Piatto piatto : piatti) {
            System.out.println(piatto.getNome());
            System.out.println(piatto.getIngredienti());

        }
    }
}
