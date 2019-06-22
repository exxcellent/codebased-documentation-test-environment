package com.example.bmw.vehicle.management.webservice.common;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;
import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

@WebFilter(urlPatterns = "/*")
public class ActivateCorsServletFilter implements Filter {

    private static final Logger LOG = Logger.getLogger( ActivateCorsServletFilter.class.getName() );

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        LOG.fine(String.format("Filter request: %s -> response %s.", servletRequest, servletResponse));

        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS");
        response.setHeader("Access-Control-Allow-Origin", sanitize(request.getHeader("origin")));
        response.setHeader("Access-Control-Allow-Credentials", "true");

        final String requestHeader = request.getHeader("Access-Control-Request-Headers");
        if (!Strings.isNullOrEmpty(requestHeader)) {
            response.setHeader("Access-Control-Allow-Headers", sanitize(requestHeader));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private String sanitize(final String origin) {
        return (origin == null) ?
                null
                : CharMatcher.BREAKING_WHITESPACE.removeFrom(origin);
    }


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void destroy() {
    }

}
