package com.nerfthisdev.islab1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @Size(max = 182)
    private String street;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id")
    private Location town;



}
