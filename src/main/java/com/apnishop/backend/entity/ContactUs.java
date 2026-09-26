package com.apnishop.backend.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "contactus")
public class ContactUs {
    @Id
    private String id;

    private String name;
    private String email;
    private String phone;
    private String subject;

    @Column(length = 2000)
    private String message;

    private Boolean status = true;
    @Column(columnDefinition = "DATETIME")
    private Instant date;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
