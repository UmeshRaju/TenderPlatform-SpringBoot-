package com.b2b.tender_platform.modules.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponse {
    private Long userId;
    private Long companyId;
    private String email;
    private String companyName;
}
