package util;

import dao.impl.*;
import dao.interfaces.BaseDao;
import dao.interfaces.CathedraDao;
import dao.interfaces.TeacherDao;
import dao.interfaces.WorksAndJobsDao;
import model.Master;
import model.ScienceTheme;
import model.Teacher;
import service.MasterService;

public class ServiceFactory {

    private static MasterService masterService;
    private static BaseDao<Master> masterDao = new MasterDao();
    private static CathedraDao cathedraDao = new CathedraDaoImpl();
    private static BaseDao<ScienceTheme> scienceThemeDao = new ScienceThemeDao();
    private static TeacherDao teacherDao = new TeacherDaoImpl();
    private static WorksAndJobsDao worksAndJobsDao = new WorksAndJobsDaoImpl();

    private ServiceFactory(){
    }

    public static MasterService getMasterService(){
        if(masterService == null){
            masterService = new MasterService(masterDao,
                    cathedraDao,
                    scienceThemeDao,
                    teacherDao,
                    worksAndJobsDao);
        }
        return masterService;
    }

}
