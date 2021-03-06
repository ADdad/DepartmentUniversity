package com.univer.util;

import com.univer.dao.impl.*;
import com.univer.dao.interfaces.*;
import com.univer.model.Master;
import com.univer.service.ChuhalovaService;
import com.univer.service.MasterService;

public class ServiceFactory {

    private static MasterService masterService;
    private static ChuhalovaService chuhalovaService;
    private static BaseDao<Master> masterDao = new MasterDao();
    private static CathedraDao cathedraDao = new CathedraDaoImpl();
    private static ScienceThemeDao scienceThemeDao = new ScienceThemeDaoImpl();
    private static TeacherDao teacherDao = new TeacherDaoImpl();
    private static WorksAndJobsDao worksAndJobsDao = new WorksAndJobsDaoImpl();
    private static ScientistDao scientistBaseDao = new ScientistDaoImpl();
    private static PostgraduateDao postgraduateDao = new PostgraduateDao();

    private ServiceFactory() {
    }

    public static MasterService getMasterService() {
        if (masterService == null) {
            masterService = new MasterService(masterDao,
                    cathedraDao,
                    scienceThemeDao,
                    teacherDao,
                    worksAndJobsDao,
                    scientistBaseDao);
        }
        return masterService;
    }

    public static ChuhalovaService getChuhalovaService() {
        if (chuhalovaService == null) {
            chuhalovaService = new ChuhalovaService(scienceThemeDao,
                    cathedraDao,
                    postgraduateDao,
                    masterDao,
                    teacherDao);
        }
        return chuhalovaService;
    }

}
