package com.univer.dao.impl;

import com.univer.dao.config.ConnectionFactory;
import com.univer.dao.interfaces.ScientistDao;
import com.univer.model.Scientist;
import com.univer.util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScientistDaoImpl implements ScientistDao {
    @Override
    public Scientist getById(String id) {
        return null;
    }

    @Override
    public boolean add(Scientist scientist) {
        return false;
    }

    @Override
    public List<Scientist> getAll() {
        List<Scientist> scientists = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_SCIENTISTS);
            while (rs.next()) {
                Scientist user = extractScientistFromRS(rs);
                scientists.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scientists;
    }

    private Scientist extractScientistFromRS(ResultSet rs) throws SQLException {
        Scientist master = new Scientist();
        master.setScientistId(rs.getString("scientist_id"));
        master.setSecondName(rs.getString("second_name"));
        master.setPhoneNumber(rs.getString("phone_number"));
        master.setGender(rs.getString("gender"));
        return master;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(Scientist scientist) {
        return false;
    }

    @Override
    public Scientist getByName(String name) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENTIST_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractScientistFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
