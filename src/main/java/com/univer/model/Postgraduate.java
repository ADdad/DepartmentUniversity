package com.univer.model;

import java.time.LocalDate;

public class Postgraduate extends Scientist {

    private String cathedraId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String thesisTheme;
    private LocalDate thesisProtectionDate;

    public String getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(String cathedraId) {
        this.cathedraId = cathedraId;
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

    public String getThesisTheme() {
        return thesisTheme;
    }

    public void setThesisTheme(String thesisTheme) {
        this.thesisTheme = thesisTheme;
    }

    public LocalDate getThesisProtectionDate() {
        return thesisProtectionDate;
    }

    public void setThesisProtectionDate(LocalDate thesisProtectionDate) {
        this.thesisProtectionDate = thesisProtectionDate;
    }
}
