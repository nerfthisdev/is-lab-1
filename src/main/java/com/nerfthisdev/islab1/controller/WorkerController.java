package com.nerfthisdev.islab1.controller;

import com.nerfthisdev.islab1.dto.WorkerCreateDto;
import com.nerfthisdev.islab1.dto.WorkerResponseDto;
import com.nerfthisdev.islab1.dto.WorkerUpdateDto;
import com.nerfthisdev.islab1.model.enums.Status;
import com.nerfthisdev.islab1.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {
    private final WorkerService service;

    public WorkerController(WorkerService service) {
        this.service = service;
    }

    // Таблица со списком: пагинация + сортировка + фильтры (точное совпадение строк)
    @GetMapping
    public Page<WorkerResponseDto> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            @RequestParam(required = false) String name,           // точная фильтрация
            @RequestParam(required = false, name = "nameContains") String nameContains // “содержит подстроку”
    ) {
        Sort s = parseSort(sort);
        Pageable pageable = PageRequest.of(page, Math.min(size, 100), s);
        return service.list(name, pageable, nameContains);
    }

    @GetMapping("/{id}")
    public WorkerResponseDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public WorkerResponseDto create(@Valid @RequestBody WorkerCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public WorkerResponseDto update(@PathVariable Long id, @Valid @RequestBody WorkerUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Спец-операции
    @GetMapping("/count-rating-less-than")
    public long countRatingLess(@RequestParam Double rating) {
        return service.countRatingLessThan(rating);
    }

    @GetMapping("/search-by-name-contains")
    public Page<WorkerResponseDto> searchByNameContains(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        Sort s = parseSort(sort);
        return service.list(null, PageRequest.of(page, size, s), q);
    }

    @GetMapping("/distinct-statuses")
    public Set<Status> distinctStatuses() {
        return service.distinctStatuses();
    }

    @PostMapping("/{id}/hire")
    public WorkerResponseDto hire(@PathVariable Long id) {
        return service.hire(id);
    }

    @PostMapping("/{id}/fire")
    public WorkerResponseDto fire(@PathVariable Long id) {
        return service.fire(id);
    }

    private Sort parseSort(String[] sortParams) {
        // формат: ?sort=id,asc&sort=name,desc
        Sort sort = Sort.unsorted();
        for (String p : sortParams) {
            String[] parts = p.split(",");
            if (parts.length == 2) {
                sort = sort.and(Sort.by("desc".equalsIgnoreCase(parts[1]) ? Sort.Direction.DESC : Sort.Direction.ASC, parts[0]));
            } else if (parts.length == 1 && !parts[0].isBlank()) {
                sort = sort.and(Sort.by(parts[0]).ascending());
            }
        }
        return sort;
    }
}
