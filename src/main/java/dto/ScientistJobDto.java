package dto;

import model.ScienceTheme;

import java.sql.Date;

public class ScientistJobDto {
    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private ScienceTheme scienceTheme;
    private String workerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ScienceTheme getScienceTheme() {
        return scienceTheme;
    }

    public void setScienceTheme(ScienceTheme scienceTheme) {
        this.scienceTheme = scienceTheme;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }
}
