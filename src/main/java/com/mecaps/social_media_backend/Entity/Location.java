package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.Country;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    private String city;

    @Enumerated(EnumType.STRING)
    private Country country;

    private Double latitude;
    private Double longitude;

    private String state;

    @OneToOne
    private User user;

    private String ip;
}

