package com.b2b.tender_platform.modules.tender.repository;

import com.b2b.tender_platform.modules.tender.entity.Tender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderRepository extends JpaRepository<Tender, Long> {

    Page<Tender> findByCompanyId(Long companyId, Pageable pageable);
    Page<Tender> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
