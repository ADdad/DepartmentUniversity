package model;

import java.sql.Date;

public class ScientistJob {
    private String name;
    private Date startDate;
    private Date endDate;
    private String scientist;
    private String scienceTheme;

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

    public String getScientist() {
        return scientist;
    }

    public void setScientist(String scientist) {
        this.scientist = scientist;
    }

    public String getScienceTheme() {
        return scienceTheme;
    }

    public void setScienceTheme(String scienceTheme) {
        this.scienceTheme = scienceTheme;
    }
}
