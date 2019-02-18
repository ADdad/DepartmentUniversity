package model;

public class Teacher extends Scientist {
    private String cathedraId;
    private String position;
    private String degree;

    public String getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(String cathedraId) {
        this.cathedraId = cathedraId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
