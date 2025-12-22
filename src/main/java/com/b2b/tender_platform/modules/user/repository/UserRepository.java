package com.b2b.tender_platform.modules.user.repository;

import com.b2b.tender_platform.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean findByEmail(String email);

    boolean existsByEmail(String email);
}
