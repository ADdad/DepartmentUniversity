package dto;

import model.ScienceTheme;
import model.Scientist;

import java.util.List;

public class ScientificWorkDto {
    private String id;
    private String name;
    private String jobType;
    private int yearOfJob;
    private List<Scientist> authors;
    private List<ScienceTheme> scienceThemes;

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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getYearOfJob() {
        return yearOfJob;
    }

    public void setYearOfJob(int yearOfJob) {
        this.yearOfJob = yearOfJob;
    }

    public List<Scientist> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Scientist> authors) {
        this.authors = authors;
    }

    public List<ScienceTheme> getScienceThemes() {
        return scienceThemes;
    }

    public void setScienceThemes(List<ScienceTheme> scienceThemes) {
        this.scienceThemes = scienceThemes;
    }
}
