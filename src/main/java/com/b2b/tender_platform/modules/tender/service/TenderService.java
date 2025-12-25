package com.b2b.tender_platform.modules.tender.service;

import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.tender.entity.Tender;
import com.b2b.tender_platform.modules.tender.repository.TenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TenderService {

    private final TenderRepository tenderRepository;

    public Tender createTender(Company company, String title, String description, LocalDate deadline, Integer budget) {
        Tender tender = Tender.builder()
                .company(company)
                .title(title)
                .description(description)
                .deadline(deadline)
                .budget(budget)
                .build();

        return tenderRepository.save(tender);
    }

    public Page<Tender> getAll(Pageable pageable) {
        return tenderRepository.findAll(pageable);
    }

    public Page<Tender> getByCompanyId(Long companyId, Pageable pageable) {
        return tenderRepository.findByCompanyId(companyId, pageable);
    }

    public Tender getById(Long id) {
        return tenderRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Tender not found with id: " + id));
    }

    public Page<Tender> searchByTitle(String keyword, Pageable pageable) {
        return tenderRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

}
