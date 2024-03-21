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

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/bonus")
@CrossOrigin
public class BonusController {

    @Autowired
    private BonusService bonusService;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/get-bonus")
    public List<BonusDTO> getBonus(){
        return bonusService.getAllBonus();
    }

    @PostMapping("/save-bonus")
    public BonusDTO saveBonus(@RequestBody BonusDTO bonusDTO){
        return bonusService.saveBonus(bonusDTO);
    }

    @PutMapping("/update-bonus")
    public BonusDTO updateBonus(@RequestBody BonusDTO bonusDTO){
        return bonusService.updateBonus(bonusDTO);
    }
    @DeleteMapping("/delete-bonus")
    public boolean deleteBonus(@RequestBody BonusDTO bonusDTO){
        return bonusService.deleteBonus(bonusDTO);
    }

    @GetMapping("/calculate-bonus")
    public ResponseEntity<Double> calculateBonus(@ModelAttribute Request request) {
        String requestedSeason = request.getSeason();
        boolean isValidSeason = Arrays.stream(BonusRate.values())
                .anyMatch(rate -> rate.getSeason().equalsIgnoreCase(requestedSeason));

        if (!isValidSeason) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid season: " + requestedSeason);
        }

        Double bonus = bonusService.calculateBonus(request.getSalary(), requestedSeason);
        return ResponseEntity.ok(bonus);
    }
    @PostMapping("/bonus-company")
    public ResponseEntity<Object> calculateAndSaveBonuses(@ModelAttribute Request request) {
        List<Company> company = companyRepository.findById(request.getCompanyId());
        if (company.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id " + request.getCompanyId());
        }else{
            String requestedSeason = request.getSeason();
            boolean isValidSeason = Arrays.stream(BonusRate.values())
                    .anyMatch(rate -> rate.getSeason().equalsIgnoreCase(requestedSeason));

            if (!isValidSeason) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid season: " + requestedSeason);
            }
        }

        Object result = bonusService.calculateAndSaveBonuses(request.getCompanyId(), request.getSeason());
        return ResponseEntity.ok(result);
    }

}

