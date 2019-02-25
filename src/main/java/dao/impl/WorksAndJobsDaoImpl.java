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
        List<ScientistJob> scientistJobs = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENTIST_JOBS_BY_WORKER_ID)) {
            stmt.setString(1, workerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                scientistJobs.add(extractScientistJobFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scientistJobs;
    }

    @Override
    public List<ScientificWork> getScientificWorksByThemeId(String themeId) {
        List<ScientificWork> scientificWorks = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENTIFIC_WORKS_BY_THEME_ID)) {
            stmt.setString(1, themeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                scientificWorks.add(extractScientificWorkFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scientificWorks;
    }

    @Override
    public List<ScientificWork> getScientificWorksByAuthorId(String authorId) {
        List<ScientificWork> scientificWorks = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENTIFIC_WORKS_BY_AUTHOR_ID)) {
            stmt.setString(1, authorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                scientificWorks.add(extractScientificWorkFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scientificWorks;
    }

    @Override
    public List<ScientistJob> getScientistJobsByThemeId(String themeId) {
        List<ScientistJob> scientistJobs = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENTIST_JOBS_BY_THEME_ID)) {
            stmt.setString(1, themeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                scientistJobs.add(extractScientistJobFromRS(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scientistJobs;
    }

    @Override
    public ScientistJob getScientistJobById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_SCIENCE_JOB_BY_ID)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            ScientistJob scientistJob = new ScientistJob();
            if(rs.next()) {

                scientistJob.setId(rs.getString("id"));
                scientistJob.setWorkerId(rs.getString("worker_id"));
                scientistJob.setScienceThemeId(rs.getString("science_theme_id"));
                scientistJob.setName(rs.getString("name"));
                scientistJob.setStartDate(rs.getDate("start_date"));
                scientistJob.setEndDate(rs.getDate("end_date"));
            }
            return scientistJob;
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
            ResultSet rs = stmt.executeQuery();
            ScientificWork scientificWork = new ScientificWork();
            if(rs.next()) {

                scientificWork.setId(rs.getString("sw.id"));
                scientificWork.setName(rs.getString("sw.name"));
                scientificWork.setJobType(rs.getString("sw.work_type"));
                scientificWork.setYearOfJob(rs.getInt("sw.year_of_job"));
            }
            return scientificWork;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private ScientistJob extractScientistJobFromRS(ResultSet rs) throws SQLException {
        ScientistJob scientistJob = new ScientistJob();
        scientistJob.setId(rs.getString("id"));
        scientistJob.setWorkerId(rs.getString("worker_id"));
        scientistJob.setScienceThemeId(rs.getString("science_theme_id"));
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
            stmt.setString(4, scientistJob.getName());
            stmt.setDate(5, scientistJob.getStartDate());
            stmt.setDate(6, scientistJob.getEndDate());
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
            ps.setString(5, scientistJob.getName());
            ps.setString(6, scientistJob.getId());
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
    public boolean deleteAuthorFromWork(String authorId, String workId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_SCIENTIST_FROM_WORK)) {
            ps.setString(1, workId);
            ps.setString(2, authorId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteThemeFromWork(String themeId, String workId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_THEME_FROM_WORK)) {
            ps.setString(1, workId);
            ps.setString(2, themeId);
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

    @Override
    public boolean deleteAllThemesFromWork(String workId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_ALL_THEMES_FROM_WORK)) {
            ps.setString(1, workId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAllAuthorsFromWork(String workId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_ALL_AUTHORS_FROM_WORK)) {
            ps.setString(1, workId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
