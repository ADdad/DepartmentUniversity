package com.univer.dao.impl;

import com.univer.dao.config.ConnectionFactory;
import com.univer.dao.interfaces.BaseDao;
import com.univer.model.Master;
import com.univer.model.Postgraduate;
import com.univer.util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostgraduateDao implements BaseDao<Postgraduate> {

    @Override
    public Postgraduate getById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_POSTGRADUATE_BY_ID)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPostgraduateFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Postgraduate extractPostgraduateFromRS(ResultSet rs) throws SQLException {
        Postgraduate postgraduate = new Postgraduate();
        postgraduate.setScientistId(rs.getString("scientist_id"));
        postgraduate.setSecondName(rs.getString("second_name"));
        postgraduate.setPhoneNumber(rs.getString("phone_number"));
        postgraduate.setGender(rs.getString("gender"));
        postgraduate.setCathedraId(rs.getString("cathedra_id"));
        postgraduate.setChiefId(rs.getString("chief_id"));
        postgraduate.setThesisTheme(rs.getString("thesis_theme"));
        postgraduate.setThesisProtectionDate(rs.getDate("thesis_protection_date"));
        postgraduate.setStartDate(rs.getDate("start_date"));
        postgraduate.setEndDate(rs.getDate("end_date"));

        return postgraduate;
    }

    @Override
    public boolean add(Postgraduate scientist) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            //TODO add check if scientist exists
            PreparedStatement ps = connection.prepareStatement(SQLQueries.INSERT_SCIENSIST);
            String newId = UUID.randomUUID().toString();
            ps.setString(1, newId);
            ps.setString(2, scientist.getSecondName());
            ps.setString(3, scientist.getPhoneNumber());
            ps.setString(4, scientist.getGender());
            int i = ps.executeUpdate();
            ps = connection.prepareStatement(SQLQueries.INSERT_POSTGRADUATE);
            ps.setString(1, newId);
            ps.setString(2, scientist.getCathedraId());
            ps.setString(3, scientist.getChiefId());
            ps.setDate(4, scientist.getStartDate());
            ps.setDate(5, scientist.getEndDate());
            ps.setString(6, scientist.getThesisTheme());
            ps.setDate(7, scientist.getThesisProtectionDate());
            i += ps.executeUpdate();
            ps.close();
            if (i == 2) {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

    @Override
    public List<Postgraduate> getAll() {
        List<Postgraduate> postgraduates = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_POSTGRADUATES);
            while (rs.next()) {
                Postgraduate user = extractPostgraduateFromRS(rs);
                postgraduates.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return postgraduates;
    }

    @Override
    public boolean delete(String scientistId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_POSTGRADUATE)) {
            //TODO add check if scientist exists
            ps.setString(1, scientistId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Postgraduate scientist) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_SCIENTIST);
            ps.setString(1, scientist.getSecondName());
            ps.setString(2, scientist.getPhoneNumber());
            ps.setString(3, scientist.getGender());
            ps.setString(4, scientist.getScientistId());
            int i = ps.executeUpdate();
            ps = connection.prepareStatement(SQLQueries.UPDATE_POSTGRADUATE);
            ps.setString(1, scientist.getCathedraId());
            ps.setString(2, scientist.getChiefId());
            ps.setString(3, scientist.getThesisTheme());
            ps.setDate(4, scientist.getStartDate());
            ps.setDate(5, scientist.getEndDate());
            ps.setDate(6, scientist.getThesisProtectionDate());
            ps.setString(7, scientist.getScientistId());
            i += ps.executeUpdate();
            return i == 2;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }
}
