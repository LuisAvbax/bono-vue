package com.example.bono_challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carbon_footprints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarbonFootprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double inputAmount;
    private Double co2;

    @Column(name = "facility_id")
    private Long facilityId;
}
