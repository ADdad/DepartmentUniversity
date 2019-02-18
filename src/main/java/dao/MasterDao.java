package dao;

import model.Master;
import util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MasterDao implements BaseDao<Master> {

    @Override
    public Master getById(String id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_MASTER_BY_ID + "'" + id + "'");
            if(rs.next())
            {
                return extractMasterFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Master extractMasterFromRS(ResultSet rs) throws SQLException {
        Master master = new Master();
        master.setScientistId( rs.getString("scientist_id") );
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

        try(Connection connection = ConnectionFactory.getConnection()) {
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
            i+=ps.executeUpdate();
            if(i == 2) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Master> getAll() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM scientists s INNER JOIN masters m ON s.scientist_id = m.scientist_id");
            List<Master> masters = new ArrayList();
            while(rs.next())
            {
                Master user = extractMasterFromRS(rs);
                masters.add(user);
            }
            return masters;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean delete(Master scientist) {
        return false;
    }

    @Override
    public boolean update(Master scientist) {
        return false;
    }
}
