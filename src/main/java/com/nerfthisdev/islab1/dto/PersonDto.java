package com.nerfthisdev.islab1.dto;

import com.nerfthisdev.islab1.model.enums.Color;
import com.nerfthisdev.islab1.model.enums.Country;
import jakarta.validation.constraints.NotNull;

public class PersonDto {
    public Long id;
    public Color eyeColor;      // nullable
    public @NotNull Color hairColor;
    public LocationDto location; // nullable
    public Integer height;      // nullable
    public @NotNull Country nationality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }
}
