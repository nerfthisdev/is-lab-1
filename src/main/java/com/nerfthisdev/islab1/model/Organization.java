package com.nerfthisdev.islab1.model;

import com.nerfthisdev.islab1.model.enums.OrganizationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "organizations")
@Check(constraints = "annual_turnover > 0 AND employees_count > 0")
public class Organization {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "official_address_id")
    private Address officialAddress; // null allowed

    @Column(name = "annual_turnover")
    @Min(1)
    private int annualTurnover;

    @Column(name = "employees_count")
    @Min(1)
    private int employeesCount;

    @Size(max = 1638)
    private String fullName; // nullable

    @Enumerated(EnumType.STRING)
    @NotNull
    private OrganizationType type;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "postal_address_id", nullable = false)
    @NotNull
    private Address postalAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
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

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    // getters/setters
    // ...
}
