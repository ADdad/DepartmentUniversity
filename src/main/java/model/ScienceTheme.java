package model;

import java.time.LocalDate;
import java.util.List;

public class ScienceTheme {
    private String id;
    private String chiefId;
    private String cathedraId;
    private String name;
    private String customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> scientificWorks;
    private List<String> scientificJobs;

    public List<String> getScientificJobs() {
        return scientificJobs;
    }

    public void setScientificJobs(List<String> scientificJobs) {
        this.scientificJobs = scientificJobs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
    }

    public String getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(String cathedraId) {
        this.cathedraId = cathedraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public List<String> getScientificWorks() {
        return scientificWorks;
    }

    public void setScientificWorks(List<String> scientificWorks) {
        this.scientificWorks = scientificWorks;
    }
}
