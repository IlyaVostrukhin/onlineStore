package net.onlineStore.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class StoreFilter extends AbstractFilter {

    @Value("${application.production}")
    private boolean isProduction;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        request.setAttribute("REQUEST_URL", requestUrl);
        try {
            chain.doFilter(request, response);
        } catch (Throwable throwable) {
            log.error("Process request failed: " + requestUrl, throwable);
            handleException(throwable, requestUrl, response);
        }
    }

    private void handleException(Throwable throwable, String requestUrl, HttpServletResponse response) throws ServletException, IOException {
        if (isProduction) {
            if (requestUrl.startsWith("/ajax") || "/error".equals(requestUrl)) {
                sendErrorStatus(response);
            } else {
                response.sendRedirect("/error?url=" + requestUrl);
            }
        } else {
            throw new ServletException(throwable);
        }
    }

    private void sendErrorStatus(HttpServletResponse response) throws IOException {
        response.reset();
        response.getWriter().write("");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
