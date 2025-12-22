package com.b2b.tender_platform.modules.application.dto;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;


@Data
@Builder
public class ApplicationResponse {
    private Long id;
    private Long tenderId;
    private Long companyId;
    private String proposal;
    private LocalDate createdAt;
}
