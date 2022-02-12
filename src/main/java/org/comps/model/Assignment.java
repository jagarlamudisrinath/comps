package org.comps.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("assignments")
public class Assignment extends BaseEntity {
    private String classId;
    private String title;
    private String description;
    private String file;
    private String createdBy;
    private int    noOfGroups;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getNoOfGroups() {
        return noOfGroups;
    }

    public void setNoOfGroups(int noOfGroups) {
        this.noOfGroups = noOfGroups;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
