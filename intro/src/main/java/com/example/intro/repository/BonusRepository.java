package com.example.intro.repository;

import com.example.intro.entity.Bonus;
import com.example.intro.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonusRepository extends JpaRepository<Bonus, Integer> {
}
