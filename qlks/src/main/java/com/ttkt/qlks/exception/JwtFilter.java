package com.ttkt.qlks.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Setter@Getter
@AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest servletRequest,
                                    jakarta.servlet.http.HttpServletResponse servletResponse,
                                    jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        HttpServletRequest request = servletRequest;
        HttpServletResponse response = servletResponse;
        // Kiểm tra và xác thực JWT token ở đây
        filterChain.doFilter(request, response);
    }
}
