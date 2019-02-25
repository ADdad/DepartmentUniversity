package service;

import dao.interfaces.*;
import dto.MasterEditDto;
import dto.MasterMainDto;
import dto.ScientistJobDto;
import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MasterService {

    private BaseDao<Master> masterDao;
    private CathedraDao cathedraDao;
    private ScienceThemeDao scienceThemeDao;
    private TeacherDao teacherDao;
    private WorksAndJobsDao worksAndJobsDao;

    public MasterService(BaseDao<Master> masterDao,
                         CathedraDao cathedraDao,
                         ScienceThemeDao scienceThemeDao,
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

    public List<ScientistJobDto> getMasterJobs(String id) {
        return worksAndJobsDao.getScientistJobsByWorkerId(id).stream()
                .map(this::migrateJobToDto)
                .collect(Collectors.toList());
    }

    private ScientistJobDto migrateJobToDto(ScientistJob scientistJob) {
        ScientistJobDto scientistJobDto = new ScientistJobDto();
        scientistJobDto.setId(scientistJob.getId());
        scientistJobDto.setWorkerId(scientistJob.getWorkerId());
        scientistJobDto.setEndDate(scientistJob.getEndDate());
        scientistJobDto.setName(scientistJob.getName());
        scientistJobDto.setStartDate(scientistJob.getStartDate());
        scientistJobDto.setScienceTheme(scienceThemeDao.getById(scientistJob.getScienceThemeId()));
        return scientistJobDto;
    }

    public void deleteMasterJob(String id) {
        worksAndJobsDao.deleteScientistJob(id);
    }

    public List<ScientistJobDto> getFilteredJobs(String scienceThemeName, Date date, Date date1, String masterId) {
        ScienceTheme scienceTheme = null;
        if(scienceThemeName != null && !scienceThemeName.isEmpty()){
            scienceTheme = scienceThemeDao.getByName(scienceThemeName);
        }
        List<ScientistJob> scientistJobs = worksAndJobsDao.getScientistJobsByWorkerId(masterId).stream()
                .filter(job -> isJobBetweenDates(job, date, date1))
                .collect(Collectors.toList());
        List<ScientistJobDto> scientistJobDtos = new ArrayList<>();
        for (ScientistJob job:
             scientistJobs) {
            if(scienceTheme != null && job.getScienceThemeId().equals(scienceTheme.getId())){
                scientistJobDtos.add(migrateJobToDto(job));
            }
            else {
                scientistJobDtos.add(migrateJobToDto(job));
            }
        }
        return scientistJobDtos;
    }

    boolean isJobBetweenDates(ScientistJob scientistJob, Date startDate, Date endDate){
        boolean isBetween = true;
        if(startDate != null){
            if(scientistJob.getEndDate() != null){
                isBetween = (scientistJob.getStartDate().after(startDate) && scientistJob.getEndDate().after(startDate));
            }
            else {
                isBetween = scientistJob.getStartDate().after(startDate);
            }
        }
        if (endDate != null){
            if(scientistJob.getEndDate() != null){
                isBetween = isBetween && (scientistJob.getStartDate().before(endDate) && scientistJob.getEndDate().before(endDate));
            }
            else {
                isBetween = isBetween && scientistJob.getStartDate().before(endDate);
            }
        }
        return isBetween;
    }

    public List<String> getScienceThemesValues() {
        return scienceThemeDao.getAll().stream().map(ScienceTheme::getName).collect(Collectors.toList());
    }

    public void addJobToMaster(ScientistJobDto jobEditDto) {
        ScienceTheme scienceTheme = scienceThemeDao.getByName(jobEditDto.getScienceTheme().getName());
        worksAndJobsDao.addScientistJob(new ScientistJob(jobEditDto.getName(),
                jobEditDto.getStartDate(),
                jobEditDto.getEndDate(),
                jobEditDto.getWorkerId(),
                scienceTheme.getId()));
    }

    public ScientistJobDto getJobToEdit(String jobId) {
       return migrateJobToDto(worksAndJobsDao.getScientistJobById(jobId));
    }

    public void updateJobOfMaster(ScientistJobDto jobEditDto) {
        ScienceTheme scienceTheme = scienceThemeDao.getByName(jobEditDto.getScienceTheme().getName());
        worksAndJobsDao.addScientistJob(new ScientistJob(jobEditDto.getName(),
                jobEditDto.getStartDate(),
                jobEditDto.getEndDate(),
                jobEditDto.getWorkerId(),
                scienceTheme.getId()));
    }
}
