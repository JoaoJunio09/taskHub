package com.joaojunio_dev.taskHub.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tb_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(length = 11)
    private String phone;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<Task> tasks;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<PushSubscription> pushSubscriptions;

    public Person() {}

    public Person(Long id, String firstName, String lastName, LocalDate birthDate, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<PushSubscription> getPushSubscriptions() {
        return pushSubscriptions;
    }

    public void setPushSubscriptions(Set<PushSubscription> pushSubscriptions) {
        this.pushSubscriptions = pushSubscriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return getId().equals(person.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
