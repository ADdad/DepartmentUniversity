package dto;

import model.Cathedra;
import model.Scientist;

import java.sql.Date;

public class MasterDto {
    private Cathedra cathedra;
    private Scientist chief;
    private String diplomaTheme;
    private Date startDate;
    private Date endDate;
    private String endReason;

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    public Scientist getChief() {
        return chief;
    }

    public void setChief(Scientist chief) {
        this.chief = chief;
    }

    public String getDiplomaTheme() {
        return diplomaTheme;
    }

    public void setDiplomaTheme(String diplomaTheme) {
        this.diplomaTheme = diplomaTheme;
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

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }
}
