package com.apnishop.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String pin;
    private String city;
    private String state;

    // Frontend sends this to know which address in the list is being edited;
    // harmless to store, not used by backend logic.
    // NOTE: column renamed to "addr_index" because "index" is a reserved
    // word in MySQL and breaks CREATE TABLE syntax.
    @Column(name = "addr_index")
    private Integer index;

    // ---- Explicit getters/setters (no Lombok needed) ----

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
