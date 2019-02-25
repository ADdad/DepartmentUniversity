package com.univer.service;

import com.univer.dao.interfaces.*;
import com.univer.dto.MasterEditDto;
import com.univer.dto.MasterMainDto;
import com.univer.dto.ScientificWorkDto;
import com.univer.dto.ScientistJobDto;
import com.univer.model.*;

import java.util.*;
//import java.util.stream.Collectors;

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
        List<MasterMainDto> master = new ArrayList<>();
        for (Master m :
                masters) {
            master.add(masterToMasterMainDto(m));
        }
        return master;
//        return masters.stream().map(this::masterToMasterMainDto).collect(Collectors.toList());
    }

    private MasterMainDto masterToMasterMainDto(Master master) {
        Cathedra mastersCathedra = cathedraDao.getById(master.getCathedraId());
        Scientist chief = getMastersChief(master.getChiefId());
        return new MasterMainDto(master.getScientistId(), chief, master.getSecondName(), master.getGender(),
                master.getDiplomaTheme(), master.getStartDate(), mastersCathedra);
    }

    public List<String> getCathedrasListForBox() {
        List<String> list = new ArrayList<>();
        for (Cathedra cathedra : cathedraDao.getAll()) {
            String name = cathedra.getName();
            list.add(name);
        }
        return list;
    }

    public List<String> getChiefsListForBox() {
        List<String> list = new ArrayList<>();
        for (Teacher teacher : teacherDao.getAll()) {
            if (isChief(teacher)) {
                String secondName = teacher.getSecondName();
                list.add(secondName);
            }
        }
        return list;
    }

    private boolean isChief(Teacher teacher) {
        Set<String> chiefsId = new HashSet<>();
        for (Master master : masterDao.getAll()) {
            String chiefId = master.getChiefId();
            chiefsId.add(chiefId);
        }
        return chiefsId.contains(teacher.getScientistId());
    }

    public void deleteMaster(String id) {
        masterDao.delete(id);
    }

    public List<MasterMainDto> getFilteredMasters(String cathedra, String chiefName) {
        List<MasterMainDto> masterMainDtos = new ArrayList<>();
        for (MasterMainDto mm :
                getMastersForMainTable()) {
            if (filterMasterByCathedraAndChief(mm, cathedra, chiefName)) {
                masterMainDtos.add(mm);
            }
        }
        return masterMainDtos;
//        return getMastersForMainTable().stream()
//                .filter(masterMainDto -> filterMasterByCathedraAndChief(masterMainDto, cathedra, chiefName))
//                .collect(Collectors.toList());
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
        List<ScientistJobDto> list = new ArrayList<>();
        for (ScientistJob scientistJob : worksAndJobsDao.getScientistJobsByWorkerId(id)) {
            ScientistJobDto scientistJobDto = migrateJobToDto(scientistJob);
            list.add(scientistJobDto);
        }
        return list;
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
//        List<ScientistJob> scientistJobs = worksAndJobsDao.getScientistJobsByWorkerId(masterId).stream()
//                .filter(job -> isJobBetweenDates(job, date, date1))
//                .collect(Collectors.toList());
        List<ScientistJob> scientistJobs = new ArrayList<>();
        for (ScientistJob sj :
                worksAndJobsDao.getScientistJobsByWorkerId(masterId)) {
            if (isJobBetweenDates(sj, date, date1)) {
                scientistJobs.add(sj);
            }
        }

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
        List<String> list = new ArrayList<>();
        for (ScienceTheme scienceTheme : scienceThemeDao.getAll()) {
            String name = scienceTheme.getName();
            list.add(name);
        }
        return list;
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
        List<ScientificWorkDto> list = new ArrayList<>();
        for (ScientificWork scientificWork : worksAndJobsDao.getScientificWorksByAuthorId(masterId)) {
            ScientificWorkDto scientificWorkDto = migrateWorkToDto(scientificWork);
            list.add(scientificWorkDto);
        }
        return list;
    }

    public List<String> getYearsValues(String masterId) {
        List<String> list = new ArrayList<>();
        for (ScientificWork scientificWork : worksAndJobsDao.getScientificWorksByAuthorId(masterId)) {
            Integer yearOfJob = scientificWork.getYearOfJob();
            String s = Objects.toString(yearOfJob);
            list.add(s);
        }
        return list;
    }

    public List<ScientificWorkDto> getFilteredWorks(String scienceThemeName, String year, String masterId) {
        List<ScientificWorkDto> scientificWorkDtos = getMastersWorks(masterId);
        List<ScientificWorkDto> scientificWorkDtos1 = new ArrayList<>();
        for (ScientificWorkDto sw :
                scientificWorkDtos) {
            if (isInThemeAndYear(sw, scienceThemeName, year)) scientificWorkDtos1.add(sw);
        }
        return scientificWorkDtos1;
    }

    private boolean isInThemeAndYear(ScientificWorkDto scientificWorkDto, String scienceThemeName, String year) {
        boolean validated = true;
        if (year != null && !year.isEmpty()) {
            validated = year.equals(Integer.toString(scientificWorkDto.getYearOfJob()));
        }
        if (validated && scienceThemeName != null && !scienceThemeName.isEmpty()) {
            Set<String> themes = new HashSet<>();
            for (ScienceTheme scienceTheme : scientificWorkDto.getScienceThemes()) {
                String name = scienceTheme.getName();
                themes.add(name);
            }
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

    public Collection<String> getAuthors(String masterId) {
        List<Scientist> authors = scientistBaseDao.getAll();
        List<String> authors2 = new ArrayList<>();
        for (Scientist st :
                authors) {
            if (!st.getScientistId().equals(masterId)) {
                authors2.add(st.getSecondName());
            }
        }
        return authors2;
//        return authors.stream().filter(sc -> !sc.getScientistId().equals(masterId))
//                .map(Scientist::getSecondName)
//                .collect(Collectors.toList());
    }

    private void dropWorkContacts(String workId) {
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
        if (worksAndJobsDao.getScientificWorksByAuthorId(masterId).size() > 0) {
            worksAndJobsDao.deleteAuthorFromWork(masterId, selectedWorkId);
        } else worksAndJobsDao.deleteScientificWork(selectedWorkId);
    }
}
