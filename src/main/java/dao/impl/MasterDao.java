package dao.impl;

import dao.config.ConnectionFactory;
import dao.interfaces.BaseDao;
import model.Master;
import util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MasterDao implements BaseDao<Master> {

    @Override
    public Master getById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_MASTER_BY_ID)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractMasterFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Master extractMasterFromRS(ResultSet rs) throws SQLException {
        Master master = new Master();
        master.setScientistId(rs.getString("scientist_id"));
        master.setSecondName(rs.getString("second_name"));
        master.setPhoneNumber(rs.getString("phone_number"));
        master.setGender(rs.getString("gender"));
        master.setCathedraId(rs.getString("cathedra_id"));
        master.setChiefId(rs.getString("chief_id"));
        master.setDiplomaTheme(rs.getString("diploma_theme"));
        master.setStartDate(rs.getDate("start_date"));
        master.setEndDate(rs.getDate("end_date"));
        master.setEndReason(rs.getString("end_reason"));

        return master;
    }

    @Override
    public boolean add(Master scientist) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            //TODO add check if scientist exists
            PreparedStatement ps = connection.prepareStatement(SQLQueries.INSERT_SCIENSIST);
            String newId = UUID.randomUUID().toString();
            ps.setString(1, newId);
            ps.setString(2, scientist.getSecondName());
            ps.setString(3, scientist.getPhoneNumber());
            ps.setString(4, scientist.getGender());
            int i = ps.executeUpdate();
            ps = connection.prepareStatement(SQLQueries.INSERT_MASTER);
            ps.setString(1, newId);
            ps.setString(2, scientist.getCathedraId());
            ps.setString(3, scientist.getChiefId());
            ps.setString(4, scientist.getDiplomaTheme());
            ps.setDate(5, scientist.getStartDate());
            ps.setDate(6, scientist.getEndDate());
            ps.setString(7, scientist.getEndReason());
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
    public List<Master> getAll() {
        List<Master> masters = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_MASTERS);
            while (rs.next()) {
                Master user = extractMasterFromRS(rs);
                masters.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return masters;
    }

    @Override
    public boolean delete(String scientistId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_MASTER)) {
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
    public boolean update(Master scientist) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_SCIENTIST);
            ps.setString(1, scientist.getSecondName());
            ps.setString(2, scientist.getPhoneNumber());
            ps.setString(3, scientist.getGender());
            ps.setString(4, scientist.getScientistId());
            int i = ps.executeUpdate();
            ps = connection.prepareStatement(SQLQueries.UPDATE_MASTER);
            ps.setString(1, scientist.getCathedraId());
            ps.setString(2, scientist.getChiefId());
            ps.setString(3, scientist.getDiplomaTheme());
            ps.setDate(4, scientist.getStartDate());
            ps.setDate(5, scientist.getEndDate());
            ps.setString(6, scientist.getEndReason());
            ps.setString(7, scientist.getScientistId());
            i += ps.executeUpdate();
            return i == 2;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }
}
