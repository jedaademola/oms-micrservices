package com.wawa.oms.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lukman Arogundade on 11/20/2019
 */
//@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LOG.info("Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        chain.doFilter(request, response);
        LOG.info("Logging Response :{}",
                res.getContentType());
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }
}
