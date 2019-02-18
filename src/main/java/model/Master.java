package model;

import java.sql.Date;

public class Master extends Scientist {
    private String cathedra_id;
    private String chiefId;
    private String diplomaTheme;
    private Date startDate;
    private Date endDate;
    private String endReason;

    public String getCathedraId() {
        return cathedra_id;
    }

    public void setCathedraId(String cathedra_id) {
        this.cathedra_id = cathedra_id;
    }

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
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

    @Override
    public String toString() {
        return super.toString() + "\nMaster{" +
                "cathedra_id='" + cathedra_id + '\'' +
                ", chiefId='" + chiefId + '\'' +
                ", diplomaTheme='" + diplomaTheme + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", endReason='" + endReason + '\'' +
                '}';
    }
}
