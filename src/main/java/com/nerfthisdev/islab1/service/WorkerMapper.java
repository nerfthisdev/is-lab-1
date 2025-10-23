package com.nerfthisdev.islab1.service;

import com.nerfthisdev.islab1.dto.*;
import com.nerfthisdev.islab1.model.*;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper {
    // Преобразование DTO → Entity (упрощённо, без “re-attach” логики)
    public Worker toEntity(WorkerCreateDto dto) {
        Worker w = new Worker();
        w.setName(dto.name);
        w.setCoordinates(toCoordinates(dto.coordinates));
        w.setOrganization(toOrganization(dto.organization));
        w.setSalary(dto.salary);
        w.setRating(dto.rating);
        w.setPosition(dto.position);
        w.setStatus(dto.status);
        w.setPerson(toPerson(dto.person));
        return w;
    }

    public void updateEntity(Worker w, WorkerUpdateDto dto) {
        w.setName(dto.name);
        w.setSalary(dto.salary);
        w.setRating(dto.rating);
        w.setPosition(dto.position);
        w.setStatus(dto.status);
        w.setCoordinates(toCoordinates(dto.coordinates));
        w.setOrganization(toOrganization(dto.organization));
        w.setPerson(toPerson(dto.person));
    }

    public WorkerResponseDto toResponse(Worker w) {
        WorkerResponseDto r = new WorkerResponseDto();
        r.id = w.getId();
        r.name = w.getName();
        r.creationDate = w.getCreationDate();
        r.salary = w.getSalary();
        r.rating = w.getRating();
        r.position = w.getPosition();
        r.status = w.getStatus();
        r.coordinates = fromCoordinates(w.getCoordinates());
        r.organization = fromOrganization(w.getOrganization());
        r.person = fromPerson(w.getPerson());
        return r;
    }

    // --- helpers (Location/Address/Organization/Person/Coordinates) ---
    private Coordinates toCoordinates(CoordinatesDto d) {
        if (d == null) return null;
        Coordinates c = new Coordinates();
        if (d.id != null) {
            c.setId(d.id);
        }
        c.setX(d.x);
        c.setY(d.y);
        return c;
    }
    private CoordinatesDto fromCoordinates(Coordinates c) {
        if (c == null) return null;
        CoordinatesDto d = new CoordinatesDto();
        d.id = c.getId();
        d.x = c.getX();
        d.y = c.getY();
        return d;
    }
    private Location toLocation(LocationDto d) {
        if (d == null) return null;
        Location l = new Location();
        if (d.id != null) {
            l.setId(d.id);
        }
        l.setX(d.x);
        l.setY(d.y);
        l.setZ(d.z);
        l.setName(d.name);
        return l;
    }
    private LocationDto fromLocation(Location l) {
        if (l == null) return null;
        LocationDto d = new LocationDto();
        d.id = l.getId();
        d.x = l.getX();
        d.y = l.getY();
        d.z = l.getZ();
        d.name = l.getName();
        return d;
    }
    private Address toAddress(AddressDto d) {
        if (d == null) return null;
        Address a = new Address();
        if (d.id != null) {
            a.setId(d.id);
        }
        a.setStreet(d.street);
        a.setTown(toLocation(d.town));
        return a;
    }
    private AddressDto fromAddress(Address a) {
        if (a == null) return null;
        AddressDto d = new AddressDto();
        d.id = a.getId();
        d.street = a.getStreet();
        d.town = fromLocation(a.getTown());
        return d;
    }
    private Organization toOrganization(OrganizationDto d) {
        if (d == null) return null;
        Organization o = new Organization();
        o.setId(d.id);
        o.setOfficialAddress(toAddress(d.officialAddress));
        o.setAnnualTurnover(d.annualTurnover);
        o.setEmployeesCount(d.employeesCount);
        o.setFullName(d.fullName);
        o.setType(d.type);
        o.setPostalAddress(toAddress(d.postalAddress));
        return o;
    }
    private OrganizationDto fromOrganization(Organization o) {
        if (o == null) return null;
        OrganizationDto d = new OrganizationDto();
        d.id = o.getId();
        d.officialAddress = fromAddress(o.getOfficialAddress());
        d.annualTurnover = o.getAnnualTurnover();
        d.employeesCount = o.getEmployeesCount();
        d.fullName = o.getFullName();
        d.type = o.getType();
        d.postalAddress = fromAddress(o.getPostalAddress());
        return d;
    }
    private Person toPerson(PersonDto d) {
        if (d == null) return null;
        Person p = new Person();
        p.setId(d.id);
        p.setEyeColor(d.eyeColor);
        p.setHairColor(d.hairColor);
        p.setLocation(toLocation(d.location));
        p.setHeight(d.height);
        p.setNationality(d.nationality);
        return p;
    }
    private PersonDto fromPerson(Person p) {
        if (p == null) return null;
        PersonDto d = new PersonDto();
        d.id = p.getId();
        d.eyeColor = p.getEyeColor();
        d.hairColor = p.getHairColor();
        d.location = fromLocation(p.getLocation());
        d.height = p.getHeight();
        d.nationality = p.getNationality();
        return d;
    }
}
