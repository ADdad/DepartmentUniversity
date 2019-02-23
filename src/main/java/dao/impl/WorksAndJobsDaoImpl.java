package dao.impl;

import dao.interfaces.WorksAndJobsDao;
import model.ScientificWork;
import model.ScientistJob;

import java.util.List;

public class WorksAndJobsDaoImpl implements WorksAndJobsDao {
    @Override
    public List<ScientistJob> getScientistJobsByWorkerId(String workerId) {
        return null;
    }

    @Override
    public List<ScientificWork> getScientificWorksByAuthorId(String authorId) {
        return null;
    }

    @Override
    public List<ScientificWork> getScientificWorksByThemeId(String themeId) {
        return null;
    }

    @Override
    public List<ScientistJob> getScientistJobsByThemeId(String themeId) {
        return null;
    }

    @Override
    public boolean addScientistJob(ScientistJob scientistJob) {
        return false;
    }

    @Override
    public boolean addScientificWork(ScientificWork scientificWork) {
        return false;
    }

    @Override
    public boolean deleteScientificWork(ScientificWork scientificWork) {
        return false;
    }

    @Override
    public boolean deleteScientistJob(ScientistJob scientistJob) {
        return false;
    }

    @Override
    public boolean updateScientistJob(ScientistJob scientistJob) {
        return false;
    }

    @Override
    public boolean updateScientificWork(ScientificWork scientificWork) {
        return false;
    }
}
