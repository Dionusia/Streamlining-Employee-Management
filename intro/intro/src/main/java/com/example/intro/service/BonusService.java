<<<<<<< HEAD:intro/src/main/java/com/example/intro/service/BonusService.java
<<<<<<< HEAD:intro/intro/src/main/java/com/example/intro/service/BonusService.java
package com.example.intro.service;

import com.example.intro.dto.BonusDTO;
import com.example.intro.dto.EmployeeDTO;
import com.example.intro.entity.Bonus;
import com.example.intro.entity.BonusRate;
import com.example.intro.entity.Company;
import com.example.intro.entity.Employee;
import com.example.intro.repository.BonusRepository;
import com.example.intro.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.log4j.Log4j2;
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
<<<<<<< HEAD
<<<<<<< HEAD
@Log4j2
=======
>>>>>>> parent of b2e81a1 (Revert "Revert "change path folder"")
=======
>>>>>>> parent of 9f464f9 (change path folder)
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
    public Double calculateBonus(Double salary, String season) {
        BonusRate bonusRate = BonusRate.valueOf(season.toUpperCase());

        return BigDecimal.valueOf(salary).multiply(BigDecimal.valueOf(bonusRate.getRate())).doubleValue();
    }

    /**
     * Calculates and saves bonuses for all employees of a company based on the provided season.
     *
     * @param companyId The ID of the company for which bonuses are to be calculated and saved.
     * @param season    The season for which bonuses are to be calculated.
     * @return Object containing a list of BonusDTO objects representing the saved bonuses, or a message indicating the company was not found.
     */
    public Object calculateAndSaveBonuses(Long companyId, String season) {

        Company company = companyRepository.findById(Math.toIntExact(companyId)).orElse(null);

        if (company == null) {
            return "Company not found with id: " + companyId;
        }

        //retrieve employees
        List<EmployeeDTO> employees = employeeService.getEmployeesByCompanyId(companyId);

        List<BonusDTO> bonusDTOList = new ArrayList<>();

        //calculate and save bonuses for each employee
        for (EmployeeDTO employeeDTO : employees) {
            Double bonusAmount = calculateBonus(employeeDTO.getSalary().doubleValue(), season);

            Bonus bonus = new Bonus();
            bonus.setEmployee(modelMapper.map(employeeDTO, Employee.class));
            bonus.setCompany(company);
            bonus.setAmount(BigDecimal.valueOf(bonusAmount));

            Bonus savedBonus = bonusRepository.save(bonus);
            bonusDTOList.add(modelMapper.map(savedBonus, BonusDTO.class));
        }

        return bonusDTOList;
    }
}
=======
package com.example.intro.service;

import com.example.intro.dto.BonusDTO;
import com.example.intro.dto.EmployeeDTO;
import com.example.intro.entity.Bonus;
import com.example.intro.entity.BonusRate;
import com.example.intro.entity.Company;
import com.example.intro.entity.Employee;
import com.example.intro.repository.BonusRepository;
import com.example.intro.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Double calculateBonus(Double salary, String season) {
        BonusRate bonusRate = BonusRate.valueOf(season.toUpperCase());

        return BigDecimal.valueOf(salary).multiply(BigDecimal.valueOf(bonusRate.getRate())).doubleValue();
    }

    /**
     * Calculates and saves bonuses for all employees of a company based on the provided season.
     *
     * @param companyId The ID of the company for which bonuses are to be calculated and saved.
     * @param season    The season for which bonuses are to be calculated.
     * @return Object containing a list of BonusDTO objects representing the saved bonuses, or a message indicating the company was not found.
     */
    public Object calculateAndSaveBonuses(Long companyId, String season) {

        Company company = companyRepository.findById(Math.toIntExact(companyId)).orElse(null);

        if (company == null) {
            return "Company not found with id: " + companyId;
        }

        //retrieve employees
        List<EmployeeDTO> employees = employeeService.getEmployeesByCompanyId(companyId);

        List<BonusDTO> bonusDTOList = new ArrayList<>();

        //calculate and save bonuses for each employee
        for (EmployeeDTO employeeDTO : employees) {
            Double bonusAmount = calculateBonus(employeeDTO.getSalary().doubleValue(), season);

            Bonus bonus = new Bonus();
            bonus.setEmployee(modelMapper.map(employeeDTO, Employee.class));
            bonus.setCompany(company);
            bonus.setAmount(BigDecimal.valueOf(bonusAmount));

            Bonus savedBonus = bonusRepository.save(bonus);
            bonusDTOList.add(modelMapper.map(savedBonus, BonusDTO.class));
        }

        return bonusDTOList;
    }
}
>>>>>>> parent of ba7e421 (Revert "change path"):intro/src/main/java/com/example/intro/service/BonusService.java
=======
package com.example.intro.service;

import com.example.intro.dto.BonusDTO;
import com.example.intro.dto.EmployeeDTO;
import com.example.intro.entity.Bonus;
import com.example.intro.entity.BonusRate;
import com.example.intro.entity.Company;
import com.example.intro.entity.Employee;
import com.example.intro.repository.BonusRepository;
import com.example.intro.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Double calculateBonus(Double salary, String season) {
        BonusRate bonusRate = BonusRate.valueOf(season.toUpperCase());

        return BigDecimal.valueOf(salary).multiply(BigDecimal.valueOf(bonusRate.getRate())).doubleValue();
    }

    /**
     * Calculates and saves bonuses for all employees of a company based on the provided season.
     *
     * @param companyId The ID of the company for which bonuses are to be calculated and saved.
     * @param season    The season for which bonuses are to be calculated.
     * @return Object containing a list of BonusDTO objects representing the saved bonuses, or a message indicating the company was not found.
     */
    public Object calculateAndSaveBonuses(Long companyId, String season) {

        Company company = companyRepository.findById(Math.toIntExact(companyId)).orElse(null);

        if (company == null) {
            return "Company not found with id: " + companyId;
        }

        //retrieve employees
        List<EmployeeDTO> employees = employeeService.getEmployeesByCompanyId(companyId);

        List<BonusDTO> bonusDTOList = new ArrayList<>();

        //calculate and save bonuses for each employee
        for (EmployeeDTO employeeDTO : employees) {
            Double bonusAmount = calculateBonus(employeeDTO.getSalary().doubleValue(), season);

            Bonus bonus = new Bonus();
            bonus.setEmployee(modelMapper.map(employeeDTO, Employee.class));
            bonus.setCompany(company);
            bonus.setAmount(BigDecimal.valueOf(bonusAmount));

            Bonus savedBonus = bonusRepository.save(bonus);
            bonusDTOList.add(modelMapper.map(savedBonus, BonusDTO.class));
        }

        return bonusDTOList;
    }
}
>>>>>>> parent of 721cb0f (change path):intro/intro/src/main/java/com/example/intro/service/BonusService.java
