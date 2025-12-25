package com.b2b.tender_platform.modules.tender.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenderListResponse {

    private Long id;
    private String title;
    private String description;
    private Long companyId;
}

