package dao.interfaces;

import model.ScienceTheme;

public interface ScienceThemeDao extends BaseDao<ScienceTheme> {
    ScienceTheme getByName(String name);
}
