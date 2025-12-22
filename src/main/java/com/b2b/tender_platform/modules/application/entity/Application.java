package com.b2b.tender_platform.modules.application.entity;

import com.b2b.tender_platform.modules.tender.entity.Tender;
import com.b2b.tender_platform.modules.company.entity.Company;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "applications")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tender_id" , nullable = false)
    private Tender tender;

    @Column(columnDefinition = "TEXT")
    private String proposal;

    @Column(nullable = false, updatable = false) private LocalDate createdAt; @Column(nullable = false) private LocalDate updatedAt; @PrePersist protected void onCreate() { this.createdAt = LocalDate.now(); this.updatedAt = LocalDate.now(); } @PreUpdate protected void onUpdate() { this.updatedAt = LocalDate.now(); }
}
