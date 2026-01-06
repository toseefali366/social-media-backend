package com.mecaps.social_media_backend.response;

public record GeoIpResponse(
        String status,
        String country,
        String regionName,
        String city,
        Double lat,
        Double lon
) {}
