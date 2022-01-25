package com.jobsearch.JobSearch.Model;

import java.util.List;

public class JobSearchCriteria {
    private String company;
    private String location;
    private String experience;
    private String description;
    private String type;
    private List<String> skills;

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getExperience() {
        return experience;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public List<String> getSkills() { return skills; }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSkills(List skills) { this.skills = skills; }
}
