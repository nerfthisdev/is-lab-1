package com.nerfthisdev.islab1.dto;

public class AddressDto {
    public Long id;
    public String street;
    public LocationDto town;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public LocationDto getTown() {
        return town;
    }

    public void setTown(LocationDto town) {
        this.town = town;
    }
}
