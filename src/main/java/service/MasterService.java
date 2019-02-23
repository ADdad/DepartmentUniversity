package service;

import dao.interfaces.BaseDao;
import dao.interfaces.WorksAndJobsDao;
import dto.MasterMainDto;
import model.*;

import java.util.List;
import java.util.stream.Collectors;

public class MasterService {

    private BaseDao<Master> masterDao;
    private BaseDao<Cathedra> cathedraDao;
    private BaseDao<ScienceTheme> scienceThemeDao;
    private BaseDao<Teacher> teacherDao;
    private WorksAndJobsDao worksAndJobsDao;

    public MasterService(BaseDao<Master> masterDao,
                         BaseDao<Cathedra> cathedraDao,
                         BaseDao<ScienceTheme> scienceThemeDao,
                         BaseDao<Teacher> teacherDao,
                         WorksAndJobsDao worksAndJobsDao) {
        this.masterDao = masterDao;
        this.cathedraDao = cathedraDao;
        this.scienceThemeDao = scienceThemeDao;
        this.teacherDao = teacherDao;
        this.worksAndJobsDao = worksAndJobsDao;
    }

    private Scientist getMastersChief(String chiefId) {
        return teacherDao.getById(chiefId);
    }

    public List<MasterMainDto> getMastersForMainTable() {
        List<Master> masters = masterDao.getAll();
        return masters.stream().map(this::masterToMasterMainDto).collect(Collectors.toList());
    }

    private MasterMainDto masterToMasterMainDto(Master master) {
        Cathedra mastersCathedra = cathedraDao.getById(master.getCathedraId());
        Scientist chief = getMastersChief(master.getChiefId());
        return new MasterMainDto(master.getScientistId(), chief, master.getSecondName(), master.getGender(),
                master.getDiplomaTheme(), master.getStartDate(), mastersCathedra);
    }
}
