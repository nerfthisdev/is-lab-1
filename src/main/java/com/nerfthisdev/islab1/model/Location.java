package com.nerfthisdev.islab1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private double x;
    private long y;

    @NotNull
    private Double z;

    @NotNull
    private String name;
}
