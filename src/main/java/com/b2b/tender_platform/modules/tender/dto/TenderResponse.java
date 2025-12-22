package com.b2b.tender_platform.modules.tender.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TenderResponse {
    private Long id;
    private String title;
    private String description;
    private Long companyId;
    private LocalDate deadline;
    private Integer budget;
    private LocalDate createdAt;
}
