package service;

import dao.interfaces.BaseDao;
import dao.interfaces.CathedraDao;
import dao.interfaces.TeacherDao;
import dao.interfaces.WorksAndJobsDao;
import dto.MasterEditDto;
import dto.MasterMainDto;
import model.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MasterService {

    private BaseDao<Master> masterDao;
    private CathedraDao cathedraDao;
    private BaseDao<ScienceTheme> scienceThemeDao;
    private TeacherDao teacherDao;
    private WorksAndJobsDao worksAndJobsDao;

    public MasterService(BaseDao<Master> masterDao,
                         CathedraDao cathedraDao,
                         BaseDao<ScienceTheme> scienceThemeDao,
                         TeacherDao teacherDao,
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

    public List<String> getCathedrasListForBox() {
        return cathedraDao.getAll().stream().map(Cathedra::getName).collect(Collectors.toList());
    }

    public List<String> getChiefsListForBox() {
        return teacherDao.getAll().stream()
                .filter(this::isChief)
                .map(Scientist::getSecondName)
                .collect(Collectors.toList());
    }

    private boolean isChief(Teacher teacher) {
        Set<String> chiefsId = masterDao.getAll().stream().map(Master::getChiefId).collect(Collectors.toSet());
        return chiefsId.contains(teacher.getScientistId());
    }

    public void deleteMaster(String id) {
        masterDao.delete(id);
    }

    public List<MasterMainDto> getFilteredMasters(String cathedra, String chiefName) {
        return getMastersForMainTable().stream()
                .filter(masterMainDto -> filterMasterByCathedraAndChief(masterMainDto, cathedra, chiefName))
                .collect(Collectors.toList());
    }

    private boolean filterMasterByCathedraAndChief(MasterMainDto masterMainDto, String cathedra, String chiefName) {
        if (cathedra != null && !cathedra.isEmpty() && chiefName != null && !chiefName.isEmpty()) {
            return masterMainDto.getCathedra().getName().equals(cathedra)
                    && masterMainDto.getChief().getSecondName().equals(chiefName);
        } else if (cathedra != null && !cathedra.isEmpty()) {
            return masterMainDto.getCathedra().getName().equals(cathedra);
        } else if (chiefName != null && !chiefName.isEmpty()) {
            return masterMainDto.getChief().getSecondName().equals(chiefName);
        }
        return true;
    }


    public void createMaster(MasterEditDto masterEditDto) {
        Cathedra cathedra = cathedraDao.getByName(masterEditDto.getCathedraName());
        Teacher chief = teacherDao.getTeacherByName(masterEditDto.getChief());
        Master newMaster = new Master();
        newMaster.setSecondName(masterEditDto.getName());
        newMaster.setGender(masterEditDto.getGender());
        newMaster.setPhoneNumber(masterEditDto.getPhone());
        newMaster.setStartDate(masterEditDto.getStartDate());
        newMaster.setEndDate(masterEditDto.getEndDate());
        newMaster.setDiplomaTheme(masterEditDto.getThemeOfDiploma());
        newMaster.setEndReason(masterEditDto.getEndReason());
        newMaster.setCathedraId(cathedra.getId());
        newMaster.setChiefId(chief.getScientistId());
        masterDao.add(newMaster);
    }
}
