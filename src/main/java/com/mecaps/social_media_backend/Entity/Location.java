package com.mecaps.social_media_backend.Entity;

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
    private Long id;

    private String city;

    private String counrty;

    private Double latitude;

    private  Double longitutde;

    private String state;
    @OneToOne
    private User userId;

    private String IP;
}
