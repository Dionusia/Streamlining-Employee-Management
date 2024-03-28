package com.example.intro.service;

import com.example.intro.dto.BonusDTO;
import com.example.intro.dto.EmployeeDTO;
import com.example.intro.entity.Bonus;
import com.example.intro.entity.BonusRate;
import com.example.intro.entity.Company;
import com.example.intro.entity.Employee;
import com.example.intro.repository.BonusRepository;
import com.example.intro.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log4j2
public class BonusService {

    @Autowired
    private BonusRepository bonusRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ModelMapper modelMapper;

    public BonusDTO saveBonus(BonusDTO bonusDTO){
        bonusRepository.save(modelMapper.map(bonusDTO, Bonus.class));
        return bonusDTO;
    }

    public List<BonusDTO> getAllBonus(){
        List<Bonus> bonusList = bonusRepository.findAll();
        return modelMapper.map(bonusList, new TypeToken<List<BonusDTO>>(){}.getType());
    }

    public BonusDTO updateBonus(BonusDTO bonusDTO){
        bonusRepository.save(modelMapper.map(bonusDTO, Bonus.class));
        return bonusDTO;
    }

    public boolean deleteBonus(BonusDTO bonusDTO){
        bonusRepository.delete(modelMapper.map(bonusDTO, Bonus.class));
        return true;
    }

    //function for calculation
    private Double calculateBonus(Double salary, String season) {
        BonusRate bonusRate = BonusRate.valueOf(season.toUpperCase());
        return BigDecimal.valueOf(salary).multiply(BigDecimal.valueOf(bonusRate.getRate())).doubleValue();
    }

    /**
     * Calculates and saves bonuses for employees of a given company and season.
     *
     * @param companyId The ID of the company whose employees' bonuses need to be calculated and saved.
     * @param season    The season for which bonuses are being calculated.
     * @return A list of BonusDTO objects representing the bonuses calculated and saved for each employee.
     *         If the company with the given ID is not found, returns a message indicating the same.
     */
    public Object calculateAndSaveBonuses(Long companyId, String season) {
        Company company = companyRepository.findById(Math.toIntExact(companyId)).orElse(null);
        if (company == null) {
            return "Company not found with id: " + companyId;
        }
        //retrieve employees
        List<EmployeeDTO> employees = employeeService.getEmployeesByCompanyId(companyId);
        List<BonusDTO> bonusDTOList = new ArrayList<>();
        //call method to calculate and save bonuses for each employee
        for (EmployeeDTO employeeDTO : employees) {
            BonusDTO bonusDTO = bonusCalculation(employeeDTO, company, season);
            bonusDTOList.add(bonusDTO);
        }
        return bonusDTOList;
    }

    /**
     * Calculates and saves the bonus for a specific employee of a given company and season.
     *
     * @param employeeDTO The data transfer object representing the employee for whom the bonus is calculated.
     * @param company     The company for which the bonus is being calculated and saved.
     * @param season      The season for which the bonus is being calculated.
     * @return A BonusDTO object representing the bonus calculated and saved for the employee.
     */
    private BonusDTO bonusCalculation(EmployeeDTO employeeDTO, Company company, String season) {
        Double bonusAmount = calculateBonus(employeeDTO.getSalary().doubleValue(), season);

        Bonus bonus = new Bonus();
        bonus.setEmployee(modelMapper.map(employeeDTO, Employee.class));
        bonus.setCompany(company);
        bonus.setAmount(BigDecimal.valueOf(bonusAmount));

        Bonus savedBonus = bonusRepository.save(bonus);

        return modelMapper.map(savedBonus, BonusDTO.class);
    }
}