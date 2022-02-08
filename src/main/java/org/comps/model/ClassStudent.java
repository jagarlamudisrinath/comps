package org.comps.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("class_students")
public class ClassStudent extends BaseEntity {
    private String classId;
    private String studentId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
