package com.b2b.tender_platform.modules.application.controller;

import com.b2b.tender_platform.modules.application.dto.ApplyTenderRequest;
import com.b2b.tender_platform.modules.application.dto.ApplicationResponse;
import com.b2b.tender_platform.modules.application.service.ApplicationService;
import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.company.service.CompanyService;
import com.b2b.tender_platform.modules.tender.entity.Tender;
import com.b2b.tender_platform.modules.tender.service.TenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final TenderService tenderService;
    private final CompanyService companyService;

    @PostMapping("/tenders/{tenderId}/apply")
    public ApplicationResponse apply(
            @PathVariable Long tenderId,
            @RequestParam Long userId,
            @Valid @RequestBody ApplyTenderRequest request) {

        Tender tender = tenderService.getById(tenderId);
        Company company = companyService.getByUserId(userId);

        return applicationService.apply(tender, company, request.getProposal());
    }

    @GetMapping("/tenders/{tenderId}")
    public List<ApplicationResponse> getApplicationsByTender(@PathVariable Long tenderId) {
        return applicationService.getByTenderId(tenderId);
    }

    @GetMapping("/companies/{companyId}")
    public List<ApplicationResponse> getApplicationsByCompany(@PathVariable Long companyId) {
        return applicationService.getByCompanyId(companyId);
    }


}
