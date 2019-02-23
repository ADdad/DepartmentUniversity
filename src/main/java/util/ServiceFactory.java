package util;

import dao.impl.*;
import service.MasterService;

public class ServiceFactory {

    private static MasterService masterService;
    private static MasterDao masterDao = new MasterDao();
    private static CathedraDao cathedraDao = new CathedraDao();
    private static ScienceThemeDao scienceThemeDao = new ScienceThemeDao();
    private static TeacherDao teacherDao = new TeacherDao();
    private static WorksAndJobsDaoImpl worksAndJobsDao = new WorksAndJobsDaoImpl();

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
