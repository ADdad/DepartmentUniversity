package dao.interfaces;

import model.ScientificWork;
import model.ScientistJob;

import java.util.List;

public interface WorksAndJobsDao{
    List<ScientistJob> getScientistJobsByWorkerId(String workerId);
    List<ScientificWork> getScientificWorksByAuthorId(String authorId);
    List<ScientificWork> getScientificWorksByThemeId(String themeId);
    List<ScientistJob> getScientistJobsByThemeId(String themeId);
    boolean addScientistJob(ScientistJob scientistJob);
    String addScientificWork(ScientificWork scientificWork);
    boolean deleteScientificWork(ScientificWork scientificWork);
    boolean deleteScientistJob(ScientistJob scientistJob);
    boolean updateScientistJob(ScientistJob scientistJob);
    boolean updateScientificWork(ScientificWork scientificWork);
    boolean deleteAuthorFromWork(String authorId);
    boolean deleteThemeFromWork(String themeId);
    boolean addWorkToScientist(String workId, String authorId);
    boolean addWorkToTheme(String workId, String themeId);
}
