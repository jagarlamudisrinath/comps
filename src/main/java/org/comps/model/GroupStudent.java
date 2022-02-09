package org.comps.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("group_students")
public class GroupStudent extends BaseEntity {
    private String groupId;
    private String studentId;

    public GroupStudent() {
    }

    public GroupStudent(String groupId, String studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
