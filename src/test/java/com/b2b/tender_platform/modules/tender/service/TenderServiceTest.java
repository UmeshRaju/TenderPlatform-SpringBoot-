package com.b2b.tender_platform.modules.tender.service;
import com.b2b.tender_platform.common.execption.NotFoundException;
import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.tender.entity.Tender;
import com.b2b.tender_platform.modules.tender.repository.TenderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TenderServiceTest {
    @Mock
    private TenderRepository tenderRepository;

    @InjectMocks
    private TenderService tenderService;

    // Helper method

    private Company mockCompany() {
        Company company = new Company();
        company.setId(1L);
        company.setName("Test Company");
        return company;
    }

    @Test
    void shouldCreateTender(){
        Company company = mockCompany();

        Tender savedTender = new Tender();

        savedTender.setId(1L);
        savedTender.setTitle("Test Tender");

        when(tenderRepository.save(any(Tender.class))).thenReturn(savedTender);

        Tender result = tenderService.createTender(
                company,
                "Test Tender",
                "Test Description",
                LocalDate.parse("10-10-2026"),
                500000
        );

        assertNotNull(result); // check result is not null
        assertEquals("Test Tender", result.getTitle()); // check title matches
        verify(tenderRepository, times(1)).save(any(Tender.class));
    }

    @Test
    void shouldReturnTenderById() {
        Tender tender = new Tender();
        tender.setId(5L);
        tender.setTitle("Bridge Project");

        when(tenderRepository.findById(5L))
                .thenReturn(Optional.of(tender));

        Tender result = tenderService.getById(5L);

        assertEquals(5L, result.getId());
        assertEquals("Bridge Project", result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenTenderNotFound() {
        when(tenderRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> tenderService.getById(99L)
        );
    }

    @Test
    void shouldReturnPaginatedTenders() {
        Tender tender = new Tender();
        tender.setId(1L);
        tender.setTitle("Metro Project");

        Pageable pageable = PageRequest.of(0, 5);
        Page<Tender> page = new PageImpl<>(List.of(tender));

        when(tenderRepository.findAll(pageable))
                .thenReturn(page);

        Page<Tender> result = tenderService.getAll(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Metro Project", result.getContent().get(0).getTitle());
    }

}
