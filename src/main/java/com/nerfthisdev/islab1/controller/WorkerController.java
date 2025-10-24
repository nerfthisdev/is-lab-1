package com.nerfthisdev.islab1.controller;

import com.nerfthisdev.islab1.dto.WorkerCreateDto;
import com.nerfthisdev.islab1.dto.WorkerResponseDto;
import com.nerfthisdev.islab1.dto.WorkerUpdateDto;
import com.nerfthisdev.islab1.model.enums.Status;
import com.nerfthisdev.islab1.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {
    private final WorkerService service;

    // Разрешённые поля для сортировки (по желанию)
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "name", "status", "position", "salary", "rating", "creationDate"
    );

    public WorkerController(WorkerService service) {
        this.service = service;
    }

    // Таблица со списком: пагинация + сортировка + фильтры (точное совпадение строк)
    @GetMapping
    public Page<WorkerResponseDto> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) List<String> sort,
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
            @RequestParam(required = false) List<String> sort
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

    /**
     * Поддерживает:
     *  - ?sort=id,asc&sort=name,desc
     *  - ?sort=id&sort=asc&sort=name,desc
     *  - ?sort=id   (по умолчанию asc)
     */
    private Sort parseSort(List<String> sortParams) {
        if (sortParams == null || sortParams.isEmpty()) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        Sort sort = Sort.unsorted();

        for (int i = 0; i < sortParams.size(); i++) {
            String token = sortParams.get(i);
            if (token == null || token.isBlank()) continue;

            String field = token.trim();
            String dir = null;

            // Вариант "field,dir"
            if (field.contains(",")) {
                String[] parts = field.split(",", 2);
                field = parts[0].trim();
                dir = parts[1].trim();
            } else if (i + 1 < sortParams.size()) {
                // Вариант "field" + следующий токен = "asc|desc"
                String next = sortParams.get(i + 1);
                if (next != null) {
                    next = next.trim();
                    if ("asc".equalsIgnoreCase(next) || "desc".equalsIgnoreCase(next)) {
                        dir = next;
                        i++; // потребили следующий как направление
                    }
                }
            }

            if (field.isBlank()) continue;

            // (опционально) пропускаем неизвестные поля
            if (!ALLOWED_SORT_FIELDS.contains(field)) continue;

            Sort.Direction direction = (dir != null && dir.equalsIgnoreCase("desc"))
                    ? Sort.Direction.DESC : Sort.Direction.ASC;

            sort = sort.and(Sort.by(direction, field));
        }

        if (sort.isUnsorted()) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        return sort;
    }
}
