package com.example.intro.controller;

import com.example.intro.dto.BonusDTO;
import com.example.intro.entity.BonusRate;
import com.example.intro.entity.Company;
import com.example.intro.entity.Request;
import com.example.intro.repository.CompanyRepository;
import com.example.intro.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/bonus")
public class BonusController {

    @Autowired
    private BonusService bonusService;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping()
    public List<BonusDTO> getBonus(){
        return bonusService.getAllBonus();
    }

    @PostMapping()
    public BonusDTO saveBonus(@RequestBody BonusDTO bonusDTO){
        return bonusService.saveBonus(bonusDTO);
    }

    @PutMapping()
    public BonusDTO updateBonus(@RequestBody BonusDTO bonusDTO){
        return bonusService.updateBonus(bonusDTO);
    }
    @DeleteMapping()
    public boolean deleteBonus(@RequestBody BonusDTO bonusDTO){
        return bonusService.deleteBonus(bonusDTO);
    }

    @GetMapping("/bonus-calculation")
    public ResponseEntity<Double> calculateBonus(Request request) {
        BonusRate requestedRate = BonusRate.resolveBySeason(request.getSeason());

        Double bonus = bonusService.calculateBonus(request.getSalary(), String.valueOf(requestedRate));
        return ResponseEntity.ok(bonus);
    }

    @PostMapping("/bonus-company")
    public ResponseEntity<Object> calculateAndSaveBonuses(Request request) {
        Optional<Company> company = companyRepository.findById(Math.toIntExact(request.getCompanyId()));
        if (company.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id " + request.getCompanyId());
        }
        BonusRate requestedRate = BonusRate.resolveBySeason(request.getSeason());
        Object result = bonusService.calculateAndSaveBonuses(request.getCompanyId(), String.valueOf(requestedRate));
        return ResponseEntity.ok(result);
    }
}

