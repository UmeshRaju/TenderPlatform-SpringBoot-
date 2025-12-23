package com.b2b.tender_platform.modules.application.service;

import com.b2b.tender_platform.modules.application.dto.ApplicationResponse;
import com.b2b.tender_platform.modules.application.entity.Application;
import com.b2b.tender_platform.modules.application.repository.ApplicationRepository;
import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.tender.entity.Tender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationResponse apply(Tender tender,Company company,String proposal) {
        if(applicationRepository.existsByTenderIdAndCompanyId(
                tender.getId(),company.getId()
        )) {
            throw new IllegalArgumentException("Already applied to tender");
        }

        Application application = Application.builder()
                .tender(tender)
                .company(company)
                .proposal(proposal)
                .build();

        Application saved = applicationRepository.save(application);

        return toResponse(saved);
    }

    // ✅ New method: get all applications for a tender
    public List<ApplicationResponse> getByTenderId(Long tenderId) {
        return applicationRepository.findByTenderId(tenderId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    // ✅ New method: get all applications for a company
    public List<ApplicationResponse> getByCompanyId(Long companyId) {
        return applicationRepository.findByCompanyId(companyId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Helper method to convert entity → DTO
    private ApplicationResponse toResponse(Application application) {
        return ApplicationResponse.builder()
            .id(application.getId())
            .tenderId(application.getTender().getId())
            .companyId(application.getCompany().getId())
            .proposal(application.getProposal())
            .createdAt(application.getCreatedAt())
            .build();
    }

}




