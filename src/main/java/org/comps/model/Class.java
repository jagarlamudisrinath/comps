package org.comps.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("classes")
public class Class extends BaseEntity {
    private String title;
    private String profId;
    private String gaId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getGaId() {
        return gaId;
    }

    public void setGaId(String gaId) {
        this.gaId = gaId;
    }
}
