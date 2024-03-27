package com.example.intro.repository;

import com.example.intro.entity.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VacationRequestRepository extends JpaRepository <VacationRequest, Integer>{
    VacationRequest findById(Long id);
}
