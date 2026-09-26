package com.apnishop.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "newsletter")
public class Newsletter {
    @Id
    private String id;

    @Column(unique = true)
    private String email;

    private Boolean status = true;

    // ---- Explicit getters/setters (no Lombok needed) ----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
