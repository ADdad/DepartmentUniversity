package model;

import java.util.List;

public abstract class Scientist {

    private String scientistId;
    private String secondName;
    private String phoneNumber;
    private String gender;
    private List<String> scientificWorks;
    private List<String> sciensistJobs;

    public List<String> getSciensistJobs() {
        return sciensistJobs;
    }

    public void setSciensistJobs(List<String> sciensistJobs) {
        this.sciensistJobs = sciensistJobs;
    }

    public List<String> getScientificWorks() {
        return scientificWorks;
    }

    public void setScientificWorks(List<String> scientificWorks) {
        this.scientificWorks = scientificWorks;
    }

    public String getScientistId() {
        return scientistId;
    }

    public void setScientistId(String scientistId) {
        this.scientistId = scientistId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Scientist{" +
                "scientistId='" + scientistId + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", scientificWorks=" + scientificWorks +
                ", sciensistJobs=" + sciensistJobs +
                '}';
    }
}
