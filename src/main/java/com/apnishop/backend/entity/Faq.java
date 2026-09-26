package com.apnishop.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "faq")
public class Faq {
    @Id
    private String id;

    @Column(length = 1000)
    private String question;

    @Column(length = 2000)
    private String answer;

    private Boolean status = true;

    // ---- Explicit getters/setters (no Lombok needed) ----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
