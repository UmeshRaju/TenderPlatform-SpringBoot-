package com.b2b.tender_platform.modules.application.repository;

import com.b2b.tender_platform.modules.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByTenderId(Long tenderId);
    List<Application> findByCompanyId(Long companyId);
    Boolean existsByTenderIdAndCompanyId(Long tenderId,Long companyId);
}
