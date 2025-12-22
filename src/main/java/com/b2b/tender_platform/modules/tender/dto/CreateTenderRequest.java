package com.b2b.tender_platform.modules.tender.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTenderRequest {
    @NotBlank
    private String title;
    private String description;

    @NotNull
    private LocalDate deadline;

    @NotNull
    @Positive
    private Integer budget;
}
