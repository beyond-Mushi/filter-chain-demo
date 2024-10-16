package org.example.filterchaindemo.securityFilter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLoggingFilter implements Filter {
    private final Logger logger =
            Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestId = request.getHeader("Request-ID");
        logger.info("Successfully authenticated request with id "+ requestId);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
