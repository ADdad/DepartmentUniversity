package dao.impl;

import dao.config.ConnectionFactory;
import dao.interfaces.WorksAndJobsDao;
import model.ScientificWork;
import model.ScientistJob;
import util.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorksAndJobsDaoImpl implements WorksAndJobsDao {
    @Override
    public List<ScientistJob> getScientistJobsByWorkerId(String workerId) {
        return getJobsByQueryAndStringParams(SQLQueries.GET_SCIENTIST_JOBS_BY_WORKER_ID, new String[]{workerId});
    }

    @Override
    public List<ScientificWork> getScientificWorksByThemeId(String themeId) {
        return getWorksByQueryAndStringParams(SQLQueries.GET_SCIENTIFIC_WORKS_BY_THEME_ID, new String[]{themeId});
    }

    @Override
    public List<ScientificWork> getScientificWorksByAuthorId(String authorId) {
        return getWorksByQueryAndStringParams(SQLQueries.GET_SCIENTIFIC_WORKS_BY_AUTHOR_ID, new String[]{authorId});
    }

    @Override
    public List<ScientistJob> getScientistJobsByThemeId(String themeId) {
        return getJobsByQueryAndStringParams(SQLQueries.GET_SCIENTIST_JOBS_BY_THEME_ID, new String[]{themeId});
    }

    private List<ScientificWork> getWorksByQueryAndStringParams(String query, String[] params) {
        List<ScientificWork> scientificWorks = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i, params[i]);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                scientificWorks.add(extractScientificWorkFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scientificWorks;
    }

    private List<ScientistJob> getJobsByQueryAndStringParams(String query, String[] params) {
        List<ScientistJob> scientistJobs = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i, params[i]);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                scientistJobs.add(extractScientistJobFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scientistJobs;
    }

    private ScientistJob extractScientistJobFromRS(ResultSet rs) throws SQLException {
        ScientistJob scientistJob = new ScientistJob();
        scientistJob.setWorkerId(rs.getString("worker_id"));
        scientistJob.setScienceThemeId(rs.getString("theme_id"));
        scientistJob.setName(rs.getString("name"));
        scientistJob.setStartDate(rs.getDate("start_date"));
        scientistJob.setEndDate(rs.getDate("end_date"));
        return scientistJob;
    }

    private ScientificWork extractScientificWorkFromRS(ResultSet rs) throws SQLException {
        ScientificWork scientificWork = new ScientificWork();
        scientificWork.setId(rs.getString("sw.id"));
        scientificWork.setName(rs.getString("sw.name"));
        scientificWork.setJobType(rs.getString("sw.work_type"));
        scientificWork.setYearOfJob(rs.getInt("sw.year_of_job"));
        return scientificWork;
    }

    @Override
    public boolean addScientistJob(ScientistJob scientistJob) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.INSERT_SCIENTIST_JOB)) {
            stmt.setString(0, scientistJob.getScienceThemeId());
            stmt.setString(1, scientistJob.getWorkerId());
            stmt.setDate(2, scientistJob.getStartDate());
            stmt.setDate(3, scientistJob.getEndDate());
            int i = stmt.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String addScientificWork(ScientificWork scientificWork) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.INSERT_SCIENTIFIC_WORK)) {
            String newId = UUID.randomUUID().toString();
            stmt.setString(0, newId);
            stmt.setString(1, scientificWork.getName());
            stmt.setString(2, scientificWork.getJobType());
            stmt.setInt(3, scientificWork.getYearOfJob());
            int i = stmt.executeUpdate();
            return i == 1 ? newId : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteScientificWork(ScientificWork scientificWork) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_SCIENTIFIC_WORK)) {
            ps.setString(0, scientificWork.getId());
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteScientistJob(ScientistJob scientistJob) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_SCIENTIST_JOB)) {
            ps.setString(0, scientistJob.getScienceThemeId());
            ps.setString(1, scientistJob.getWorkerId());
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateScientistJob(ScientistJob scientistJob) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_SCIENTIST_JOB)) {
            ps.setDate(0, scientistJob.getStartDate());
            ps.setDate(1, scientistJob.getEndDate());
            ps.setString(2, scientistJob.getScienceThemeId());
            ps.setString(3, scientistJob.getWorkerId());
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateScientificWork(ScientificWork scientificWork) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_SCIENTIFIC_WORK)) {
            ps.setString(0, scientificWork.getName());
            ps.setString(1, scientificWork.getJobType());
            ps.setInt(2, scientificWork.getYearOfJob());
            ps.setString(3, scientificWork.getId());
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAuthorFromWork(String authorId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_SCIENTIST_FROM_WORK)) {
            ps.setString(0, authorId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteThemeFromWork(String themeId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_THEME_FROM_WORK)) {
            ps.setString(0, themeId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
