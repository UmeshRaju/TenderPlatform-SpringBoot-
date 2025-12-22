package com.b2b.tender_platform.modules.tender.controller;

import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.company.service.CompanyService;
import com.b2b.tender_platform.modules.tender.dto.TenderResponse;
import com.b2b.tender_platform.modules.tender.dto.CreateTenderRequest;
import com.b2b.tender_platform.modules.tender.entity.Tender;
import com.b2b.tender_platform.modules.tender.service.TenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenders")
@RequiredArgsConstructor
public class TenderController {
    private final TenderService tenderService;
    private final CompanyService companyService;

    @PostMapping
    public TenderResponse createTender(
            @RequestParam Long userId,
            @Valid @RequestBody CreateTenderRequest request
            ) {
        Company company = companyService.getByUserId(userId);

        Tender tender = tenderService.createTender(
                company,
                request.getTitle(),
                request.getDescription(),
                request.getDeadline(),
                request.getBudget()
        );

        return TenderResponse.builder()
                .id(tender.getId())
                .title(tender.getTitle())
                .description(tender.getDescription())
                .companyId(company.getId())
                .deadline(tender.getDeadline())
                .budget(tender.getBudget())
                .createdAt(tender.getCreatedAt())
                .build();

    }
}
