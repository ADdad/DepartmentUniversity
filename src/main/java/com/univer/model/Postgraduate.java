package com.univer.model;

import java.sql.Date;

public class Postgraduate extends Scientist {

    private String cathedraId;
    private String chiefId;
    private Date startDate;
    private Date endDate;
    private String thesisTheme;
    private Date thesisProtectionDate;

    public String getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(String cathedraId) {
        this.cathedraId = cathedraId;
    }

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
    }

    public String getThesisTheme() {
        return thesisTheme;
    }

    public void setThesisTheme(String thesisTheme) {
        this.thesisTheme = thesisTheme;
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

    public Date getThesisProtectionDate() {
        return thesisProtectionDate;
    }

    public void setThesisProtectionDate(Date thesisProtectionDate) {
        this.thesisProtectionDate = thesisProtectionDate;
    }
}
