package com.univer.model;

import java.sql.Date;
import java.util.List;

public class ScienceTheme {
    private String id;
    private String chiefId;
    private String cathedraId;
    private String name;
    private String customer;
    private Date startDate;
    private Date endDate;
    private List<String> scientificWorks;
    private List<String> scientificJobs;

    public ScienceTheme(){}

    public ScienceTheme(String name) {
        this.name = name;
    }

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

    public List<String> getScientificWorks() {
        return scientificWorks;
    }

    public void setScientificWorks(List<String> scientificWorks) {
        this.scientificWorks = scientificWorks;
    }
}
