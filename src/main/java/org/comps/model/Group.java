package org.comps.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("groups")
public class Group extends BaseEntity {
    private String groupId;
    private String assignmentId;
    private String answer;
    private Boolean active = Boolean.TRUE;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
