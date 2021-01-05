package com.epam.web.rotten.potatoes.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static final String COMMAND_PARAMETER = "command";
    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String RIGHTS = "rights";
    private static final int FORBIDDEN = 403;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String command = req.getParameter(COMMAND_PARAMETER);
        if ((command != null && command.contains(ADMIN))) {
            HttpSession session = req.getSession();
            String rights = (String) session.getAttribute(RIGHTS);
            if (!ADMIN.equalsIgnoreCase(rights)) {
                resp.sendError(FORBIDDEN);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
