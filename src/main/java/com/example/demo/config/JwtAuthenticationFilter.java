package com.example.demo.config;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.Error;
import com.example.demo.services.OurUserDetailsService;
import com.example.demo.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final OurUserDetailsService ourUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtAuthenticationFilter(OurUserDetailsService ourUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.ourUserDetailsService = ourUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        try {
            if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authHeader.substring(7); // cắt "Bearer "

            // ✅ Có thể ném ra ExpiredJwtException, MalformedJwtException,...
            userEmail = jwtTokenUtil.extractTokenGetUsername(jwtToken);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = ourUserDetailsService.loadUserByUsername(userEmail);

                if (!jwtTokenUtil.isTokenValid(jwtToken, userDetails)) {
                    throw new CustomException(Error.JWT_INVALID);
                }

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new CustomException(Error.JWT_EXPIRED);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            throw new CustomException(Error.JWT_MALFORMED);
        }
    }

}
