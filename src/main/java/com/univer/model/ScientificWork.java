package com.univer.model;

import java.util.List;

public class ScientificWork {
    private String id;
    private String name;
    private String jobType;
    private int yearOfJob;
    private List<String> authors;
    private List<String> scienceThemes;

    public List<String> getScienceThemes() {
        return scienceThemes;
    }

    public void setScienceThemes(List<String> scienceThemes) {
        this.scienceThemes = scienceThemes;
    }

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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
