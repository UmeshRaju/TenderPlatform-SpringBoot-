package com.b2b.tender_platform.modules.user.controller;

import com.b2b.tender_platform.modules.company.entity.Company;
import com.b2b.tender_platform.modules.company.service.CompanyService;
import com.b2b.tender_platform.modules.user.dto.UserRegisterRequest;
import com.b2b.tender_platform.modules.user.dto.UserRegisterResponse;
import com.b2b.tender_platform.modules.user.entity.User;
import com.b2b.tender_platform.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;

    @PostMapping("/register")
    public UserRegisterResponse register(
            @Valid @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        User user = userService.register(
                userRegisterRequest.getEmail(),
                userRegisterRequest.getPassword()
        );

        Company company = companyService.createCompany(
                user,
                userRegisterRequest.getCompanyName(),
                userRegisterRequest.getIndustry(),
                userRegisterRequest.getDescription()
        );

        return UserRegisterResponse.builder()
                .userId(user.getId())
                .companyId(company.getId())
                .email(user.getEmail())
                .companyName(company.getName())
                .build();
    }
}
