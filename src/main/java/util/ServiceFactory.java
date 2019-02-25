package util;

import dao.impl.*;
import dao.interfaces.*;
import model.Master;
import model.Scientist;
import service.MasterService;

public class ServiceFactory {

    private static MasterService masterService;
    private static BaseDao<Master> masterDao = new MasterDao();
    private static CathedraDao cathedraDao = new CathedraDaoImpl();
    private static ScienceThemeDao scienceThemeDao = new ScienceThemeDaoImpl();
    private static TeacherDao teacherDao = new TeacherDaoImpl();
    private static WorksAndJobsDao worksAndJobsDao = new WorksAndJobsDaoImpl();
    private static ScientistDao scientistBaseDao = new ScientistDaoImpl();

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

}
