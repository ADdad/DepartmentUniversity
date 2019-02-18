package model;

import java.time.LocalDate;

public class SciensistJob {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String scientist;
    private String scienceTheme;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
