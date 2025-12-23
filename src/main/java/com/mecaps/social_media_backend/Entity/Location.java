package com.mecaps.social_media_backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private String state;
    @OneToOne
    private User user;
    private String ipAddress;
}
