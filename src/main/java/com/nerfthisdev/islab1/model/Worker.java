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
}