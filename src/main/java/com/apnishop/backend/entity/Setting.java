package com.apnishop.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "setting")
public class Setting {
    @Id
    private String id;

    private String siteName;
    private String map1;
    private String map2;
    private String address;
    private String email;
    private String phone;
    private String whatsapp;
    private String twitter;
    private String facebook;
    private String linkdin;
    private String instagram;
    private String youtube;

    @Column(columnDefinition = "TEXT CHARACTER SET utf8mb4")
    private String privacyPolicy;

    @Column(columnDefinition = "TEXT CHARACTER SET utf8mb4")
    private String termsCondition;

    @Column(columnDefinition = "TEXT CHARACTER SET utf8mb4")
    private String refundPolicy;

    // ---- Explicit getters/setters (no Lombok needed) ----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getMap1() {
        return map1;
    }

    public void setMap1(String map1) {
        this.map1 = map1;
    }

    public String getMap2() {
        return map2;
    }

    public void setMap2(String map2) {
        this.map2 = map2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkdin() {
        return linkdin;
    }

    public void setLinkdin(String linkdin) {
        this.linkdin = linkdin;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public String getTermsCondition() {
        return termsCondition;
    }

    public void setTermsCondition(String termsCondition) {
        this.termsCondition = termsCondition;
    }

    public String getRefundPolicy() {
        return refundPolicy;
    }

    public void setRefundPolicy(String refundPolicy) {
        this.refundPolicy = refundPolicy;
    }
}
