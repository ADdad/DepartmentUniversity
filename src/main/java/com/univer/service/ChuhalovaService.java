package com.univer.service;

import com.univer.dao.impl.MasterDao;
import com.univer.dao.impl.PostgraduateDao;
import com.univer.dao.interfaces.BaseDao;
import com.univer.dao.interfaces.CathedraDao;
import com.univer.dao.interfaces.ScienceThemeDao;
import com.univer.dao.interfaces.TeacherDao;
import com.univer.model.*;

import java.util.List;

public class ChuhalovaService {

    private ScienceThemeDao scienceThemeDao;
    private CathedraDao cathedraDao;
    private PostgraduateDao postgraduateDao;
    private BaseDao<Master> masterDao;
    private TeacherDao teacherDao;

    public ChuhalovaService() {
    }

    public ChuhalovaService(ScienceThemeDao scienceThemeDao,
                            CathedraDao cathedraDao,
                            PostgraduateDao postgraduateDao,
                            BaseDao<Master> masterDao,
                            TeacherDao teacherDao) {
        this.cathedraDao = cathedraDao;
        this.teacherDao = teacherDao;
        this.scienceThemeDao = scienceThemeDao;
        this.postgraduateDao = postgraduateDao;
        this.masterDao = masterDao;
    }

    public List<ScienceTheme> getScienceThemes() {
        return scienceThemeDao.getAll();
    }

    public List<Cathedra> getCathedras() {
        return cathedraDao.getAll();
    }

    public void updateScienceTheme(ScienceTheme scienceTheme) {
        scienceThemeDao.update(scienceTheme);
    }

    public void createScienceTheme(ScienceTheme scienceTheme) {
        scienceThemeDao.add(scienceTheme);
    }

    public void deleteScienceTheme(String id) {
        scienceThemeDao.delete(id);
    }

    public List<Postgraduate> getAllPostgraduates() {
        return postgraduateDao.getAll();
    }

    public List<Master> getMasters(){
        return masterDao.getAll();
    }

    public List<Teacher> getTeachers(){
        return teacherDao.getAll();
    }
}
