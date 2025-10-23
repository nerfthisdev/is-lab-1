package com.nerfthisdev.islab1.model;

import com.nerfthisdev.islab1.model.enums.Color;
import com.nerfthisdev.islab1.model.enums.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "persons")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Color eyeColor; // nullable

    @Enumerated(EnumType.STRING)
    @NotNull
    private Color hairColor;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location; // nullable

    @Min(1)
    private Integer height; // nullable, but if present >0

    @Enumerated(EnumType.STRING)
    @NotNull
    private Country nationality;


}

