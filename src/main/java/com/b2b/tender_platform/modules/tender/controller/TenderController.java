package com.b2b.tender_platform.modules.tender.controller;

import com.b2b.tender_platform.common.api.PageResponse;
import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.company.service.CompanyService;
import com.b2b.tender_platform.modules.tender.dto.TenderListResponse;
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

    @GetMapping
    public PageResponse<TenderListResponse> getAllTenders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        var pageable =  org.springframework.data.domain.PageRequest.of(page, size);
        var tenderPage = tenderService.getAll(pageable);

        return PageResponse.<TenderListResponse>builder()
                .content(
                        tenderPage.getContent().stream()
                                .map(t -> TenderListResponse.builder()
                                        .id(t.getId())
                                        .title(t.getTitle())
                                        .description(t.getDescription())
                                        .companyId(t.getCompany().getId())
                                        .build())
                                .toList()
                )
                .page(tenderPage.getNumber())
                .size(tenderPage.getSize())
                .totalElements((tenderPage.getTotalElements()))
                .totalPages(tenderPage.getTotalPages())
                .last(tenderPage.isLast())
                .build();

    }

    @GetMapping("company/{companyId}")
    public PageResponse<TenderListResponse> getTendersByCompany(
            @PathVariable Long companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var pageable = org.springframework.data.domain.PageRequest.of(page, size);
        var tenderPage = tenderService.getByCompanyId(companyId, pageable);

        return PageResponse.<TenderListResponse>builder()
                .content(
                        tenderPage.getContent().stream()
                                .map(t -> TenderListResponse.builder()
                                        .id(t.getId())
                                        .title(t.getTitle())
                                        .description((t.getDescription()))
                                        .companyId(t.getCompany().getId())
                                        .build())
                                .toList()
                )
                .page(tenderPage.getNumber())
                .size(tenderPage.getSize())
                .totalElements(tenderPage.getTotalElements())
                .totalPages(tenderPage.getTotalPages())
                .last(tenderPage.isLast())
                .build();
    }

    @GetMapping("/search")
    public PageResponse<TenderListResponse> searchTenders(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        var pageable = org.springframework.data.domain.PageRequest.of(page, size);
        var tenderPage = tenderService.searchByTitle(keyword, pageable);

        return PageResponse.<TenderListResponse>builder()
                .content(
                        tenderPage.getContent().stream()
                                .map(t -> TenderListResponse.builder()
                                        .id(t.getId())
                                        .title(t.getTitle())
                                        .description(t.getDescription())
                                        .companyId(t.getCompany().getId())
                                        .build())
                                .toList()
                )
                .page(tenderPage.getNumber())
                .size(tenderPage.getSize())
                .totalElements(tenderPage.getTotalElements())
                .totalPages(tenderPage.getTotalPages())
                .last(tenderPage.isLast())
                .build();
    }

}
