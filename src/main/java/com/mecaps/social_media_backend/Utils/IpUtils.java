package com.mecaps.social_media_backend.Utils;

import jakarta.servlet.http.HttpServletRequest;

public class IpUtils {
    public static String getClientIp(HttpServletRequest request) {
        String[] headers = {"X-Forwarded-For",
                "X-Real-IP",
                "CF-Connecting-IP",
                "Forwarded"
        };
        for (String header : headers) {
            String value = request.getHeader(header);
            if (value != null && !value.isBlank() && !"unknown".equalsIgnoreCase(value)) {
                return value.split(",")[0].trim();
            }
        }
        String ip = request.getRemoteAddr();

        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}
