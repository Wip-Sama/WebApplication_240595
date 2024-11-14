package com.dipartimento.demowebapplications.persistence.dao.impljdbc;

import com.dipartimento.demowebapplications.model.Piatto;
import com.dipartimento.demowebapplications.model.Ristorante;
import com.dipartimento.demowebapplications.persistence.DBManager;
import com.dipartimento.demowebapplications.persistence.dao.RistoranteDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RistoranteDaoJDBC implements RistoranteDao {
    Connection conn = null;
    public RistoranteDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public List<Ristorante> findAll() {
        List<Ristorante> ristoranti = new ArrayList<Ristorante>();
        String query = "select * from ristorante";
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Ristorante rist = new Ristorante();
                rist.setNome(rs.getString("nome"));
                rist.setDescrizione(rs.getString("descrizione"));
                rist.setUbicazione(rs.getString("ubicazione"));
                ristoranti.add(rist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ristoranti;
    }

    @Override
    public Ristorante findByPrimaryKey(String nome) {
        Ristorante ristorante = new Ristorante();
        String query = "select * from ristorante where nome = ?";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ristorante.setNome(rs.getString("nome"));
            ristorante.setDescrizione(rs.getString("descrizione"));
            ristorante.setUbicazione(rs.getString("ubicazione"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ristorante;
    }

    @Override
    public void save(Ristorante ristorante) {
        String query = "insert into ristorante (nome, descrizione, ubicazione) values (?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, ristorante.getDescrizione());
            st.setString(2, ristorante.getUbicazione());
            st.setString(3, ristorante.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Ristorante ristorante) {
        String query = "delete from ristorante where nome = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, ristorante.getNome());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        RistoranteDao ristoDao = DBManager.getInstance().getRistoranteDao();
        List<Ristorante> ristoranti = ristoDao.findAll();
        for (Ristorante ristorante : ristoranti) {
            System.out.println(ristorante.getNome());
            System.out.println(ristorante.getDescrizione());
            System.out.println(ristorante.getUbicazione());
        }
    }
}


