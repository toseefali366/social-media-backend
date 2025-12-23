package com.mecaps.social_media_backend.Security;

import com.mecaps.social_media_backend.Service.TokenBlackListService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailService userDetailsService;
    private final TokenBlackListService tokenBlackListService;

    public JwtAuthFilter(JwtService jwtService, CustomUserDetailService userDetailsService, TokenBlackListService tokenBlackListService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.tokenBlackListService = tokenBlackListService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            email = jwtService.extractEmail(token);
        }
        if (tokenBlackListService.isBlacklisted(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has been logged out");
            return;
        }

        if (email != null && SecurityContextHolder.getContext()
                        .getAuthentication() == null){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if(jwtService.IstokenValid(token)) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        }
        filterChain.doFilter(request,response);
    }

}

