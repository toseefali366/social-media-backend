package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Location;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.repository.LocationRepository;
import com.mecaps.social_media_backend.response.GeoIpResponse;
import com.mecaps.social_media_backend.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final GeoLocationService geoLocationService;

    public void saveOrUpdateLocation(User user, HttpServletRequest request) {
        String ipAddress = IpUtils.getClientIp(request);

        Location location = locationRepository.findByUser(user)
                .orElse(new Location());

        location.setUser(user);
        location.setIpAddress(ipAddress);

        GeoIpResponse geo = geoLocationService.getLocationByIp(ipAddress);
        if (geo != null && "success".equalsIgnoreCase(geo.status())) {
            location.setCountry(geo.country());
            location.setState(geo.regionName());
            location.setCity(geo.city());
            location.setLatitude(geo.lat());
            location.setLongitude(geo.lon());
        } else {
            location.setCountry("Unknown");
            location.setState("Unknown");
            location.setCity("Unknown");
            location.setLatitude(null);
            location.setLongitude(null);
        }

        locationRepository.save(location);
    }
}
