package org.comps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.xml.bind.annotation.XmlTransient;

abstract class BaseEntity implements Persistable<String> {
    @Id
    private String id;

    @JsonIgnore
    @Transient
    private boolean isNew = false;

    @Override
    @XmlTransient
    public boolean isNew() {
        return isNew;
    }

    @Override
    @XmlTransient
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
