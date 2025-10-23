package com.nerfthisdev.islab1.model;

import com.nerfthisdev.islab1.model.enums.Position;
import com.nerfthisdev.islab1.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Entity
@Table(name = "workers",
        uniqueConstraints = { @UniqueConstraint(name = "uq_workers_id", columnNames = "id") })
@Check(constraints = "salary > 0 AND rating > 0")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // генерируется БД

    @NotBlank
    private String name;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "coordinates_id", nullable = false)
    @NotNull
    private Coordinates coordinates;

    @Column(nullable = false)
    private LocalDate creationDate; // генерим автоматически

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", nullable = false)
    @NotNull
    private Organization organization;

    @Min(1)
    private double salary;

    @NotNull
    private Double rating; // >0 в @Check

    @Enumerated(EnumType.STRING)
    @NotNull
    private Position position;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    @NotNull
    private Person person;

    @PrePersist
    public void prePersist() {
        if (creationDate == null) creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}