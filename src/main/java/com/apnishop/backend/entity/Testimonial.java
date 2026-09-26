package com.apnishop.backend.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "testimonial")
public class Testimonial {
    @Id
    private String id;

    private String user;
    private String product;

    private String username;
    private String pname;

    @Column(length = 2000)
    private String message;

    private Integer star;
    @Column(columnDefinition = "DATETIME")
    private Instant date;

    // ---- Explicit getters/setters (no Lombok needed) ----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
