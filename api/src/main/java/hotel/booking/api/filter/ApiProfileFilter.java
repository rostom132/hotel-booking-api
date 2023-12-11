package hotel.booking.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ApiProfileFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        // Process the request
        filterChain.doFilter(request, response);

        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;

        // Log API metrics
        logApiMetrics(request, processingTime);
    }

    private void logApiMetrics(HttpServletRequest request, long processingTime) {
        // This function will push profile log to log service, for further analysing

        String ipAddress = request.getRemoteAddr();
        String apiEndpoint = request.getRequestURI();
        String httpMethod = request.getMethod();

        // Log the metrics
        System.out.println("IP: " + ipAddress + ", HTTP Method: " + httpMethod +
                ", API Endpoint: " + apiEndpoint + ", Processing Time: " + processingTime + " ms");
    }
}