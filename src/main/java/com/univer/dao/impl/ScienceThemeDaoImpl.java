package com.univer.dao.impl;

import com.univer.dao.config.ConnectionFactory;
import com.univer.dao.interfaces.ScienceThemeDao;
import com.univer.dao.interfaces.WorksAndJobsDao;
import com.univer.model.ScienceTheme;
import com.univer.model.ScientistJob;
import com.univer.util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScienceThemeDaoImpl implements ScienceThemeDao {
    WorksAndJobsDao worksAndJobsDao = new WorksAndJobsDaoImpl();

    @Override
    public ScienceTheme getById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENCE_THEME_BY_ID)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractScienceThemeFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private ScienceTheme extractScienceThemeFromRS(ResultSet rs) throws SQLException {
        ScienceTheme scienceTheme = new ScienceTheme();
        scienceTheme.setId(rs.getString("id"));
        scienceTheme.setChiefId(getChiefId(scienceTheme));
        scienceTheme.setCathedraId(rs.getString("cathedra_id"));
        scienceTheme.setName(rs.getString("name"));
        scienceTheme.setCustomer(rs.getString("customer"));
        scienceTheme.setStartDate(rs.getDate("start_date"));
        scienceTheme.setEndDate(rs.getDate("end_date"));
        return scienceTheme;
    }

    @Override
    public boolean add(ScienceTheme scienceTheme) {
        if(scienceTheme.getChiefId() != null){
            ScientistJob scientistJob = new ScientistJob();
            scientistJob.setScienceThemeId(scienceTheme.getId());
            scientistJob.setWorkerId(scienceTheme.getChiefId());
            scientistJob.setName("theme chief");
            worksAndJobsDao.addScientistJob(scientistJob);
        }
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.INSERT_SCIENCE_THEME);) {
            String newId = UUID.randomUUID().toString();
            ps.setString(1, newId);
            ps.setString(2, scienceTheme.getCathedraId());
            ps.setString(3, scienceTheme.getName());
            ps.setString(4, scienceTheme.getCustomer());
            ps.setDate(5, scienceTheme.getStartDate());
            ps.setDate(6, scienceTheme.getEndDate());
            int i = ps.executeUpdate();
            ps.close();
                return i == 1;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public String getChiefId(ScienceTheme scienceTheme){
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENCE_THEME_CHIEF)) {
            stmt.setString(1, scienceTheme.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ScienceTheme> getAll() {
        List<ScienceTheme> scienceThemes = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_SCIENCE_THEMES);
            while (rs.next()) {
                ScienceTheme scienceTheme = extractScienceThemeFromRS(rs);
                scienceThemes.add(scienceTheme);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scienceThemes;
    }

    @Override
    public boolean delete(String scienceThemeId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_SCIENCE_THEME)) {
            ps.setString(1, scienceThemeId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ScienceTheme scienceTheme) {
        if(scienceTheme.getChiefId() != null){
            ScientistJob scientistJob = new ScientistJob();
            scientistJob.setScienceThemeId(scienceTheme.getId());
            scientistJob.setWorkerId(scienceTheme.getChiefId());
            scientistJob.setName("theme chief");
            worksAndJobsDao.addScientistJob(scientistJob);
        }
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_SCIENCE_THEME)) {
            ps.setString(2, scienceTheme.getCathedraId());
            ps.setString(3, scienceTheme.getName());
            ps.setString(4, scienceTheme.getCustomer());
            ps.setDate(5, scienceTheme.getStartDate());
            ps.setDate(6, scienceTheme.getEndDate());
            ps.setString(7, scienceTheme.getId());
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

    @Override
    public ScienceTheme getByName(String name) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENCE_THEME_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractScienceThemeFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ScienceTheme> getThemesOfWork(String workId) {
        List<ScienceTheme> scienceThemes = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENCE_THEMES_OF_WORK)) {
            stmt.setString(1, workId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ScienceTheme scienceTheme = extractScienceThemeFromRS(rs);
                scienceThemes.add(scienceTheme);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scienceThemes;
    }
}
