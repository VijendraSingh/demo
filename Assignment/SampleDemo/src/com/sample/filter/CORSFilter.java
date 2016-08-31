package com.sample.filter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Cors filter class allow request to any domain
 * @author Vijendra Singh
 * @since
 * @version 1.0
 */
public class CORSFilter extends OncePerRequestFilter {

    /**
     * This is filter method for request
     * @param HttpServletRequest request object
     * @param HttpServletResponse response object
     * @param FilterChain filterChain object
     * @see FilterChain doFiltemethod
     */
    @Override
    protected final void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "1");
        }
        filterChain.doFilter(request, response);
    }

}
