package com.apnishop.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "maincategory")
public class MainCategory {
    @Id
    private String id;

    private String name;
    private String pic;
    private Boolean status = true;

    // ---- Explicit getters/setters (no Lombok needed) ----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
