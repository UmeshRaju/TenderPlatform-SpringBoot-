package com.b2b.tender_platform.modules.company.entity;
import com.b2b.tender_platform.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    private String industry;

    private String description;

    @Column(name = "logo_url")
    private String logoUrl;
}
