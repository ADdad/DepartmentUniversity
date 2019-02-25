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

    @Override
    public ScientistJob getScientistJobById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENCE_JOB_BY_ID)) {
            stmt.setString(1, id);
            ScientistJob rs = extractScientistJobFromRS(stmt.executeQuery());
            if (rs != null) return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ScientificWork getScientificWorkId(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENTIFIC_WORK_BY_ID)) {
            stmt.setString(1, id);
            ScientificWork rs = extractScientificWorkFromRS(stmt.executeQuery());
            if (rs != null) return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private List<ScientificWork> getWorksByQueryAndStringParams(String query, String[] params) {
        List<ScientificWork> scientificWorks = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 1; i < params.length; i++) {
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
            for (int i = 1; i < params.length; i++) {
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
        scientistJob.setId(rs.getString("id"));
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
            String newId = UUID.randomUUID().toString();
            stmt.setString(1, newId);
            stmt.setString(2, scientistJob.getScienceThemeId());
            stmt.setString(3, scientistJob.getWorkerId());
            stmt.setDate(4, scientistJob.getStartDate());
            stmt.setDate(5, scientistJob.getEndDate());
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
            stmt.setString(1, newId);
            stmt.setString(2, scientificWork.getName());
            stmt.setString(3, scientificWork.getJobType());
            stmt.setInt(4, scientificWork.getYearOfJob());
            int i = stmt.executeUpdate();
            return i == 1 ? newId : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteScientificWork(String scientificWorkId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_SCIENTIFIC_WORK)) {
            ps.setString(1, scientificWorkId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteScientistJob(String scientificJobId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_SCIENTIST_JOB)) {
            ps.setString(1, scientificJobId);
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
            ps.setDate(1, scientistJob.getStartDate());
            ps.setDate(2, scientistJob.getEndDate());
            ps.setString(3, scientistJob.getScienceThemeId());
            ps.setString(4, scientistJob.getWorkerId());
            ps.setString(5, scientistJob.getId());
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
            ps.setString(1, scientificWork.getName());
            ps.setString(2, scientificWork.getJobType());
            ps.setInt(3, scientificWork.getYearOfJob());
            ps.setString(4, scientificWork.getId());
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
            ps.setString(1, authorId);
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
            ps.setString(1, themeId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addWorkToScientist(String workId, String authorId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.ADD_WORK_TO_SCIENTIST)) {
            stmt.setString(1, workId);
            stmt.setString(2, authorId);
            int i = stmt.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addWorkToTheme(String workId, String themeId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.ADD_WORK_TO_THEME)) {
            stmt.setString(1, workId);
            stmt.setString(2, themeId);
            int i = stmt.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
