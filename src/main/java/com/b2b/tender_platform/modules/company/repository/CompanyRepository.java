package com.b2b.tender_platform.modules.company.repository;

import com.b2b.tender_platform.modules.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByUserId(Long userId);
    boolean existsByNameIgnoreCase(Long userId);
}
