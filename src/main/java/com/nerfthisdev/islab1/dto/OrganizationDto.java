package com.nerfthisdev.islab1.dto;

import com.nerfthisdev.islab1.model.enums.OrganizationType;

public class OrganizationDto {
    public Long id;
    public AddressDto officialAddress; // nullable
    public int annualTurnover;
    public int employeesCount;
    public String fullName; // nullable
    public OrganizationType type;
    public AddressDto postalAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressDto getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(AddressDto officialAddress) {
        this.officialAddress = officialAddress;
    }

    public int getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(int annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public AddressDto getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(AddressDto postalAddress) {
        this.postalAddress = postalAddress;
    }
}
