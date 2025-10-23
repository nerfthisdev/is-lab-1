package com.nerfthisdev.islab1.dto;

import com.nerfthisdev.islab1.model.enums.Position;
import com.nerfthisdev.islab1.model.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WorkerCreateDto {
    @NotBlank
    public String name;
    @Valid
    @NotNull
    public CoordinatesDto coordinates;
    @Valid
    @NotNull
    public OrganizationDto organization;
    @Min(1)
    public double salary;
    @NotNull
    public Double rating;
    @NotNull
    public Position position;
    @NotNull
    public Status status;
    @Valid
    @NotNull
    public PersonDto person;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordinatesDto getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }

    public OrganizationDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDto organization) {
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

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }
}