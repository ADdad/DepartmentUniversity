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
        masterDao.add(extractMasterFromEditDto(masterEditDto));
    }

    private Master extractMasterFromEditDto(MasterEditDto masterEditDto) {
        Cathedra cathedra = cathedraDao.getByName(masterEditDto.getCathedraName());
        Teacher chief = teacherDao.getTeacherByName(masterEditDto.getChief());
        Master newMaster = new Master();
        newMaster.setScientistId(masterEditDto.getId());
        newMaster.setSecondName(masterEditDto.getName());
        newMaster.setGender(masterEditDto.getGender());
        newMaster.setPhoneNumber(masterEditDto.getPhone());
        newMaster.setStartDate(masterEditDto.getStartDate());
        newMaster.setEndDate(masterEditDto.getEndDate());
        newMaster.setDiplomaTheme(masterEditDto.getThemeOfDiploma());
        newMaster.setEndReason(masterEditDto.getEndReason());
        newMaster.setCathedraId(cathedra.getId());
        newMaster.setChiefId(chief.getScientistId());
        return newMaster;
    }

    public void updateMaster(MasterEditDto masterEditDto) {
        masterDao.update(extractMasterFromEditDto(masterEditDto));
    }

    public MasterEditDto getMasterToEdit(String selectedMasterId) {
        MasterEditDto masterEditDto = new MasterEditDto();
        Master master = masterDao.getById(selectedMasterId);
        Cathedra cathedra = cathedraDao.getById(master.getCathedraId());
        Teacher chief = teacherDao.getById(master.getChiefId());
        masterEditDto.setId(selectedMasterId);
        masterEditDto.setChief(chief.getSecondName());
        masterEditDto.setCathedraName(cathedra.getName());
        masterEditDto.setEndReason(master.getEndReason());
        masterEditDto.setEndDate(master.getEndDate());
        masterEditDto.setStartDate(master.getStartDate());
        masterEditDto.setThemeOfDiploma(master.getDiplomaTheme());
        masterEditDto.setPhone(master.getPhoneNumber());
        masterEditDto.setGender(master.getGender());
        masterEditDto.setName(master.getSecondName());
        return masterEditDto;
    }
}
