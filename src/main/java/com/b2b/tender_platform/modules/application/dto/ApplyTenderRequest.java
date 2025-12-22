package com.b2b.tender_platform.modules.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplyTenderRequest {
    @NotBlank
    private String proposal;
}
