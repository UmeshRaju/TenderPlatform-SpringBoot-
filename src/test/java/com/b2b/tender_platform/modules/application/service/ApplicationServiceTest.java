package com.b2b.tender_platform.modules.application.service;

import com.b2b.tender_platform.common.execption.NotFoundException;
import com.b2b.tender_platform.modules.application.dto.ApplicationResponse;
import com.b2b.tender_platform.modules.application.entity.Application;
import com.b2b.tender_platform.modules.application.repository.ApplicationRepository;
import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.tender.entity.Tender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    // Helper Methods

    private Company mockCompany() {
        Company company = new Company();
        company.setId(1L);
        company.setName("Applicant Company");
        return company;
    }

    private Tender mockTender() {
        Tender tender = new Tender();
        tender.setId(2L);
        tender.setTitle("Road Project");
        return tender;
    }

    @Test
    void shouldApplyToTenderSuccessfully() {

        Company company = mockCompany();
        Tender tender = mockTender();

        when(applicationRepository.existsByTenderIdAndCompanyId(
                tender.getId(), company.getId()
        )).thenReturn(false);

        Application savedApplication = new Application();
        savedApplication.setId(10L);

        when(applicationRepository.save(any(Application.class))).thenReturn(savedApplication);

        ApplicationResponse result = applicationService.apply(
                tender,
                company,
                "We can complete in 6 months"
        );

    }

    void shouldThrowExceptionWhenAlreadyApplied() {
            Company company = mockCompany();
            Tender tender = mockTender();

            when(applicationRepository.existsByTenderIdAndCompanyId(
                    tender.getId(), company.getId()
            )).thenReturn(true);

            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> applicationService.apply(
                            tender,
                            company,
                            "Duplicate proposal"
                    )
            );

        assertEquals("Already applied to this tender", ex.getMessage());

        verify(applicationRepository, never())
                .save(any(Application.class));
    }
}
