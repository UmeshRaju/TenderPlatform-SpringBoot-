package com.b2b.tender_platform.modules.company.service;

import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import com.b2b.tender_platform.modules.user.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompany(User user,String name,String Industry,String description) {
        Company company = Company.builder().name(name).industry(Industry).description(description).build();

        return companyRepository.save(company);
    }

    public Company getByUserId(Long userId) {
        return companyRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
    }
}
