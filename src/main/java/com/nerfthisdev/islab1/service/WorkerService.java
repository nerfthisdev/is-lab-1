package com.nerfthisdev.islab1.service;

import com.nerfthisdev.islab1.dto.WorkerCreateDto;
import com.nerfthisdev.islab1.dto.WorkerResponseDto;
import com.nerfthisdev.islab1.dto.WorkerUpdateDto;
import com.nerfthisdev.islab1.model.enums.Status;
import com.nerfthisdev.islab1.model.Worker;
import com.nerfthisdev.islab1.repository.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class WorkerService {
    private final WorkerRepository repo;
    private final WorkerMapper mapper;
    private final DomainEventPublisher events;

    public WorkerService(WorkerRepository repo, WorkerMapper mapper, DomainEventPublisher events) {
        this.repo = repo;
        this.mapper = mapper;
        this.events = events;
    }

    public Page<WorkerResponseDto> list(String exactName, Pageable pageable, String containsName) {
        Page<Worker> page;
        if (exactName != null) {
            page = repo.findByName(exactName, pageable);
        } else if (containsName != null) {
            page = repo.findByNameContainingIgnoreCase(containsName, pageable);
        } else {
            page = repo.findAll(pageable);
        }
        return page.map(mapper::toResponse);
    }

    public WorkerResponseDto get(Long id) {
        return mapper.toResponse(find(id));
    }

    public WorkerResponseDto create(WorkerCreateDto dto) {
        Worker saved = repo.save(mapper.toEntity(dto));
        events.emit("workers", "created:" + saved.getId());
        return mapper.toResponse(saved);
    }

    public WorkerResponseDto update(Long id, WorkerUpdateDto dto) {
        Worker w = find(id);
        mapper.updateEntity(w, dto);
        Worker saved = repo.save(w);
        events.emit("workers", "updated:" + saved.getId());
        return mapper.toResponse(saved);
    }

    public void delete(Long id) {
        Worker w = find(id);
        if (w.getOrganization() != null && w.getOrganization().getEmployeesCount() > 0) {
            throw new IllegalStateException("Невозможно удалить работника: связан с организацией (employeesCount > 0)");
        }
        repo.delete(w);
        events.emit("workers", "deleted:" + id);
    }

    public long countRatingLessThan(Double rating) { return repo.countByRatingLessThan(rating); }
    public Set<Status> distinctStatuses() { return repo.findDistinctStatusBy(); }

    public WorkerResponseDto hire(Long id) {
        Worker w = find(id);
        w.setStatus(Status.HIRED);
        events.emit("workers", "updated:" + id);
        return mapper.toResponse(w);
    }
    public WorkerResponseDto fire(Long id) {
        Worker w = find(id);
        w.setStatus(Status.FIRED);
        events.emit("workers", "updated:" + id);
        return mapper.toResponse(w);
    }

    private Worker find(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Worker " + id + " not found"));
    }
}
