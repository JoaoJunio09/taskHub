package com.joaojunio_dev.taskHub.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class PushSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String endpoint;

    @Column(length = 500)
    private String p256dh;

    @Column(length = 500)
    private String auth;

    @ManyToOne
    private Person person;

    public PushSubscription() {}

    public PushSubscription(Long id, String endpoint, String p256dh, String auth, Person person) {
        this.id = id;
        this.endpoint = endpoint;
        this.p256dh = p256dh;
        this.auth = auth;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getP256dh() {
        return p256dh;
    }

    public void setP256dh(String p256dh) {
        this.p256dh = p256dh;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PushSubscription that = (PushSubscription) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
