package dto;

import model.Cathedra;
import model.Scientist;

import java.sql.Date;

public class MasterMainDto {
    private String id;
    private Scientist chief;
    private String name;
    private String gender;
    private String diplomaTheme;
    private Date startOfMaster;
    private Cathedra cathedra;

    public MasterMainDto(){}

    public MasterMainDto(String id, Scientist chief, String name, String gender, String diplomaTheme, Date startOfMaster, Cathedra cathedra) {
        this.id = id;
        this.chief = chief;
        this.name = name;
        this.gender = gender;
        this.diplomaTheme = diplomaTheme;
        this.startOfMaster = startOfMaster;
        this.cathedra = cathedra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Scientist getChief() {
        return chief;
    }

    public void setChief(Scientist chief) {
        this.chief = chief;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiplomaTheme() {
        return diplomaTheme;
    }

    public void setDiplomaTheme(String diplomaTheme) {
        this.diplomaTheme = diplomaTheme;
    }

    public Date getStartOfMaster() {
        return startOfMaster;
    }

    public void setStartOfMaster(Date startOfMaster) {
        this.startOfMaster = startOfMaster;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }
}
