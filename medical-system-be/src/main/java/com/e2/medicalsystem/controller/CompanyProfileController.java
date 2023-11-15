package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.CompanyProfileDto;
import com.e2.medicalsystem.model.CompanyProfile;
import com.e2.medicalsystem.service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/company")
public class CompanyProfileController {
    private CompanyProfileService companyProfileService;
    @Autowired
    public CompanyProfileController(CompanyProfileService companyProfileService)
    {
        this.companyProfileService = companyProfileService;
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<CompanyProfileDto>> getAllCompanies() {

        List<CompanyProfile> allCompanies = companyProfileService.getAllCompanies();
        List<CompanyProfileDto> allCompaniesDto = new ArrayList<>();
        for(CompanyProfile companyProfile : allCompanies)
        {
            allCompaniesDto.add(new CompanyProfileDto(companyProfile));
        }
        return new ResponseEntity<>(allCompaniesDto, HttpStatus.OK);

    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CompanyProfileDto> getCompanyById(@PathVariable Integer id){
        CompanyProfile companyProfile = companyProfileService.getCompanyById(id);
        CompanyProfileDto companyProfileDto = new CompanyProfileDto(companyProfile);
        return new ResponseEntity<>(companyProfileDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyProfile> updateCompanyProfile(@PathVariable long id, @RequestBody CompanyProfile updatedProfile) {
        updatedProfile.setId(id);

        CompanyProfile updatedCompany = companyProfileService.updateCompanyProfile(updatedProfile);

        if (updatedCompany != null) {
            return ResponseEntity.ok(updatedCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
