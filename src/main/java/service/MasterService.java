package service;

import dao.interfaces.*;
import dto.MasterEditDto;
import dto.MasterMainDto;
import dto.ScientificWorkDto;
import dto.ScientistJobDto;
import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class MasterService {

    private BaseDao<Master> masterDao;
    private CathedraDao cathedraDao;
    private ScienceThemeDao scienceThemeDao;
    private TeacherDao teacherDao;
    private WorksAndJobsDao worksAndJobsDao;
    private ScientistDao scientistBaseDao;

    public MasterService(BaseDao<Master> masterDao,
                         CathedraDao cathedraDao,
                         ScienceThemeDao scienceThemeDao,
                         TeacherDao teacherDao,
                         WorksAndJobsDao worksAndJobsDao,
                         ScientistDao scientistBaseDao) {
        this.scientistBaseDao = scientistBaseDao;
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

    private ScientificWorkDto migrateWorkToDto(ScientificWork scientificWork) {
        ScientificWorkDto scientificWorkDto = new ScientificWorkDto();
        scientificWorkDto.setId(scientificWork.getId());
        scientificWorkDto.setJobType(scientificWork.getJobType());
        scientificWorkDto.setName(scientificWork.getName());
        scientificWorkDto.setYearOfJob(scientificWork.getYearOfJob());
        scientificWorkDto.setScienceThemes(scienceThemeDao.getThemesOfWork(scientificWork.getId()));
        return scientificWorkDto;
    }

    public void deleteMasterJob(String id) {
        worksAndJobsDao.deleteScientistJob(id);
    }

    public List<ScientistJobDto> getFilteredJobs(String scienceThemeName, Date date, Date date1, String masterId) {
        ScienceTheme scienceTheme = null;
        if (scienceThemeName != null && !scienceThemeName.isEmpty()) {
            scienceTheme = scienceThemeDao.getByName(scienceThemeName);
        }
        List<ScientistJob> scientistJobs = worksAndJobsDao.getScientistJobsByWorkerId(masterId).stream()
                .filter(job -> isJobBetweenDates(job, date, date1))
                .collect(Collectors.toList());
        List<ScientistJobDto> scientistJobDtos = new ArrayList<>();
        for (ScientistJob job :
                scientistJobs) {
            if (scienceTheme != null && job.getScienceThemeId().equals(scienceTheme.getId())) {
                scientistJobDtos.add(migrateJobToDto(job));
            } else {
                scientistJobDtos.add(migrateJobToDto(job));
            }
        }
        return scientistJobDtos;
    }

    boolean isJobBetweenDates(ScientistJob scientistJob, Date startDate, Date endDate) {
        boolean isBetween = true;
        if (startDate != null) {
            if (scientistJob.getEndDate() != null) {
                isBetween = (scientistJob.getStartDate().after(startDate) && scientistJob.getEndDate().after(startDate));
            } else {
                isBetween = scientistJob.getStartDate().after(startDate);
            }
        }
        if (endDate != null) {
            if (scientistJob.getEndDate() != null) {
                isBetween = isBetween && (scientistJob.getStartDate().before(endDate) && scientistJob.getEndDate().before(endDate));
            } else {
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
        worksAndJobsDao.addScientistJob(new ScientistJob(null, jobEditDto.getName(),
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
        worksAndJobsDao.updateScientistJob(new ScientistJob(jobEditDto.getId(),
                jobEditDto.getName(),
                jobEditDto.getStartDate(),
                jobEditDto.getEndDate(),
                jobEditDto.getWorkerId(),
                scienceTheme.getId()));
    }

    public List<ScientificWorkDto> getMastersWorks(String masterId) {
        return worksAndJobsDao.getScientificWorksByAuthorId(masterId).stream()
                .map(this::migrateWorkToDto)
                .collect(Collectors.toList());
    }

    public List<String> getYearsValues(String masterId) {
        return worksAndJobsDao.getScientificWorksByAuthorId(masterId).stream()
                .map(ScientificWork::getYearOfJob)
                .map(Objects::toString)
                .collect(Collectors.toList());
    }

    public List<ScientificWorkDto> getFilteredWorks(String scienceThemeName, String year, String masterId) {
        return getMastersWorks(masterId).stream()
                .filter(sw -> isInThemeAndYear(sw, scienceThemeName, year))
                .collect(Collectors.toList());
    }

    private boolean isInThemeAndYear(ScientificWorkDto scientificWorkDto, String scienceThemeName, String year) {
        boolean validated = true;
        if (year != null && !year.isEmpty()) {
            validated = year.equals(Integer.toString(scientificWorkDto.getYearOfJob()));
        }
        if (validated && scienceThemeName != null && !scienceThemeName.isEmpty()) {
            Set<String> themes = scientificWorkDto.getScienceThemes().stream().map(ScienceTheme::getName).collect(Collectors.toSet());
            validated = themes.contains(scienceThemeName);
        }
        return validated;
    }

    public void addWorkToMaster(ScientificWorkDto workDto, String masterId) {
        ScientificWork scientificWork = new ScientificWork();
        scientificWork.setName(workDto.getName());
        scientificWork.setJobType(workDto.getJobType());
        scientificWork.setYearOfJob(workDto.getYearOfJob());
        String workId = worksAndJobsDao.addScientificWork(scientificWork);
        for (String themeName :
                workDto.getThemeNames()) {
            worksAndJobsDao.addWorkToTheme(workId, scienceThemeDao.getByName(themeName).getId());
        }
        worksAndJobsDao.addWorkToScientist(workId, masterId);
        for (String authorName :
                workDto.getAuthorsNames()) {
            worksAndJobsDao.addWorkToScientist(workId, scientistBaseDao.getByName(authorName).getScientistId());
        }
    }

    public Collection<Object> getAuthors(String masterId) {
        List<Scientist> authors = scientistBaseDao.getAll();
        return authors.stream().filter(sc -> !sc.getScientistId().equals(masterId))
                .map(Scientist::getSecondName)
                .collect(Collectors.toList());
    }

    private void dropWorkContacts(String workId){
        worksAndJobsDao.deleteAllAuthorsFromWork(workId);
        worksAndJobsDao.deleteAllThemesFromWork(workId);
    }

    public void updateWork(ScientificWorkDto workDto, String masterId) {
        ScientificWork scientificWork = new ScientificWork();
        scientificWork.setName(workDto.getName());
        scientificWork.setJobType(workDto.getJobType());
        scientificWork.setYearOfJob(workDto.getYearOfJob());
        scientificWork.setId(workDto.getId());
        worksAndJobsDao.updateScientificWork(scientificWork);
        dropWorkContacts(workDto.getId());
        for (String themeName :
                workDto.getThemeNames()) {
            worksAndJobsDao.addWorkToTheme(workDto.getId(), scienceThemeDao.getByName(themeName).getId());
        }
        worksAndJobsDao.addWorkToScientist(workDto.getId(), masterId);
        for (String authorName :
                workDto.getAuthorsNames()) {
            worksAndJobsDao.addWorkToScientist(workDto.getId(), scientistBaseDao.getByName(authorName).getScientistId());
        }
    }

    public ScientificWorkDto getWorkToEdit(String selectedWorkId) {
        return migrateWorkToDto(worksAndJobsDao.getScientificWorkId(selectedWorkId));
    }

    public void deleteMasterWork(String selectedWorkId, String masterId) {
        if(worksAndJobsDao.getScientificWorksByAuthorId(masterId).size() > 0){
            worksAndJobsDao.deleteAuthorFromWork(masterId, selectedWorkId);
        }
        else worksAndJobsDao.deleteScientificWork(selectedWorkId);
    }
}
