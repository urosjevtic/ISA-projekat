package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.CompanyProfile;
import com.e2.medicalsystem.repository.CompanyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyProfileService {

    private final CompanyProfileRepository companyProfileRepository;

    @Autowired
    public CompanyProfileService(CompanyProfileRepository companyProfileRepository)
    {
        this.companyProfileRepository = companyProfileRepository;
    }

    public List<CompanyProfile> getAllCompanies(){ return companyProfileRepository.findAll(); }
    public CompanyProfile getCompanyById(long id)
    {
        return companyProfileRepository.findById(id).orElse(null);
    }

    public CompanyProfile updateCompanyProfile(CompanyProfile updatedProfile) {
        CompanyProfile existingProfile = companyProfileRepository.findById(updatedProfile.getId()).orElse(null);

        if (existingProfile != null) {
            existingProfile.setName(updatedProfile.getName());
            existingProfile.setAddress(updatedProfile.getAddress());
            existingProfile.setDescription(updatedProfile.getDescription());
            existingProfile.setCompanyLogo(updatedProfile.getCompanyLogo());

            return companyProfileRepository.save(existingProfile);
        }

        return null;
    }
}
