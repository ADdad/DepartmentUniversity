package model;

import java.sql.Date;

public class ScientistJob {
    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String workerId;
    private String scienceThemeId;

    public ScientistJob(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ScientistJob(String id, String name, Date startDate, Date endDate, String workerId, String scienceThemeId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workerId = workerId;
        this.scienceThemeId = scienceThemeId;
    }

    public ScientistJob(String workerId, String scienceThemeId) {
        this.workerId = workerId;
        this.scienceThemeId = scienceThemeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getScienceThemeId() {
        return scienceThemeId;
    }

    public void setScienceThemeId(String scienceThemeId) {
        this.scienceThemeId = scienceThemeId;
    }
}
