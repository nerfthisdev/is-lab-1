package com.nerfthisdev.islab1.repository;

import com.nerfthisdev.islab1.model.Worker;
import com.nerfthisdev.islab1.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    long countByRatingLessThan(Double rating);

    Page<Worker> findByName(String name, Pageable pageable);

    Page<Worker> findByNameContainingIgnoreCase(String substr, Pageable pageable);

    Set<Status> findDistinctStatusBy();
}