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

    // getters/setters
    // ...
}
