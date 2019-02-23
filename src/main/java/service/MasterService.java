package service;

import dao.interfaces.BaseDao;
import dao.interfaces.WorksAndJobsDao;
import model.Cathedra;
import model.Master;
import model.ScienceTheme;
import model.Scientist;

public class MasterService {

    private BaseDao<Master> masterDao;
    private BaseDao<Cathedra> cathedraDao;
    private BaseDao<ScienceTheme> scienceThemeDao;
    private WorksAndJobsDao worksAndJobsDao;

    public MasterService(BaseDao<Master> masterDao,
                         BaseDao<Cathedra> cathedraDao,
                         BaseDao<ScienceTheme> scienceThemeDao,
                         WorksAndJobsDao worksAndJobsDao) {
        this.masterDao = masterDao;
        this.cathedraDao = cathedraDao;
        this.scienceThemeDao = scienceThemeDao;
        this.worksAndJobsDao = worksAndJobsDao;
    }

    private Scientist getMastersChief(String chiefId){
        return null;
    }
}
