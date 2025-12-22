package com.b2b.tender_platform.modules.user.service;

import com.b2b.tender_platform.modules.user.entity.User;
import com.b2b.tender_platform.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(String email, String password) {
        if(userRepository.findByEmail(email)){
            throw new IllegalArgumentException("email already exists");
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
    }




}
