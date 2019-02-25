package com.univer.dao.interfaces;

import com.univer.model.ScienceTheme;

import java.util.List;

public interface ScienceThemeDao extends BaseDao<ScienceTheme> {
    ScienceTheme getByName(String name);
    List<ScienceTheme> getThemesOfWork(String workId);
}
