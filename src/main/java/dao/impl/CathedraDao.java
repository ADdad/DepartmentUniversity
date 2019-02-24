package dao.impl;

import dao.config.ConnectionFactory;
import dao.interfaces.BaseDao;
import model.Cathedra;
import util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CathedraDao implements BaseDao<Cathedra> {
    @Override
    public Cathedra getById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_CATHEDRA_BY_ID)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cathedra(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Cathedra cathedra) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.INSERT_CATHEDRA)) {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, cathedra.getName());
            stmt.setString(3, cathedra.getPhoneNumber());
            int i = stmt.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cathedra> getAll() {
        List<Cathedra> cathedras = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_CATHEDRAS);
            while (rs.next()) {
                Cathedra cathedra = new Cathedra(rs.getString(1), rs.getString(2), rs.getString(3));
                cathedras.add(cathedra);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cathedras;
    }

    @Override
    public boolean delete(String cathedraId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_CATHEDRA)) {
            ps.setString(1, cathedraId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Cathedra cathedra) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_CATHEDRA)) {
                ps.setString(1, cathedra.getName());
                ps.setString(2, cathedra.getPhoneNumber());
                ps.setString(3, cathedra.getId());
                int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }
}
