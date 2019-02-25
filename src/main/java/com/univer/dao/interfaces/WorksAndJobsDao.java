package com.univer.dao.interfaces;

import com.univer.model.ScientificWork;
import com.univer.model.ScientistJob;

import java.util.List;

public interface WorksAndJobsDao{
    List<ScientistJob> getScientistJobsByWorkerId(String workerId);
    List<ScientificWork> getScientificWorksByAuthorId(String authorId);
    List<ScientificWork> getScientificWorksByThemeId(String themeId);
    List<ScientistJob> getScientistJobsByThemeId(String themeId);
    ScientistJob getScientistJobById(String id);
    ScientificWork getScientificWorkId(String id);
    boolean addScientistJob(ScientistJob scientistJob);
    String addScientificWork(ScientificWork scientificWork);
    boolean deleteScientificWork(String scientificWorkId);
    boolean deleteScientistJob(String scientistJobId);
    boolean updateScientistJob(ScientistJob scientistJob);
    boolean updateScientificWork(ScientificWork scientificWork);
    boolean deleteAuthorFromWork(String authorId, String workId);
    boolean deleteThemeFromWork(String themeId, String workId);
    boolean addWorkToScientist(String workId, String authorId);
    boolean addWorkToTheme(String workId, String themeId);
    boolean deleteAllThemesFromWork(String workId);
    boolean deleteAllAuthorsFromWork(String workId);
}
