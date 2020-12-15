package com.epam.web.rotten.potatoes.controller;

import com.epam.web.rotten.potatoes.model.Rights;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static final String COMMAND_PARAMETER = "command";
    private static final String ADMIN = "admin";
    private static final String RIGHTS = "rights";
    private static final String ADMIN_RIGHTS = "ADMIN";
    private static final int FORBIDDEN = 403;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String command = req.getParameter(COMMAND_PARAMETER);
        if (command != null && command.contains(ADMIN)) {
            HttpSession session = req.getSession();
            Rights rights = (Rights) session.getAttribute(RIGHTS);
            String stringRights = rights.toString();
            if (!ADMIN_RIGHTS.equalsIgnoreCase(stringRights)) {
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
