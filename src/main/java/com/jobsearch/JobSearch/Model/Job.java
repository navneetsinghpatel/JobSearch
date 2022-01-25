package com.jobsearch.JobSearch.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Job {
    @Id
    @SequenceGenerator(
            name = "job_sequence",
            sequenceName = "job_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "job_sequence"
    )
    private Long jobId;
    private String company;
    private String location;
    private String experience;
    private String description;
    private String type;
    private Long contact;
    @ElementCollection
    private List<String> skills;

    public Job() {
    }

    public Job(Long jobId, String company, String location, String experience, String description, String type, Long contact, List skills) {
        this.jobId = jobId;
        this.company = company;
        this.location = location;
        this.experience = experience;
        this.description = description;
        this.type = type;
        this.contact = contact;
        this.skills = skills;
    }

    public Job(String company, String location, String experience, String description, String type, Long contact, List skills) {
        this.company = company;
        this.location = location;
        this.experience = experience;
        this.description = description;
        this.type = type;
        this.contact = contact;
        this.skills = skills;
    }

    public Long getJobId() {
        return jobId;
    }

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

    public Long getContact() {
        return contact;
    }

    public List getSkills() { return skills; }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

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

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public void setSkills(List skills) { this.skills = skills; }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", experience='" + experience + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", contact=" + contact +
                ", skills=" + skills.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return company.equals(job.company) && location.equals(job.location) && experience.equals(job.experience) && description.equals(job.description) && Objects.equals(type, job.type) && Objects.equals(contact, job.contact) && skills.equals(job.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, location, experience, description, type, contact, skills);
    }
}
