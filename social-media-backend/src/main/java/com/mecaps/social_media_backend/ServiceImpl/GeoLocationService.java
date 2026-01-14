package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Response.GeoIpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeoLocationService {
    private final RestTemplate restTemplate;

    public GeoIpResponse getLocationByIp(String ip) {
        if (ip == null || ip.startsWith("127.") ||
                ip.startsWith("192.168.") ||
                ip.startsWith("10.") ||
                ip.equals("0:0:0:0:0:0:1")) {
            return null;
        }
        try {
            String url = "http://ip-api.com/json/" + ip;
            return restTemplate.getForObject(url, GeoIpResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

}
