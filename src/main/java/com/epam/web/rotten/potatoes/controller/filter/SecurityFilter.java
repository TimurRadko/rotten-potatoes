package com.epam.web.rotten.potatoes.controller.filter;

import com.epam.web.rotten.potatoes.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SecurityFilter implements Filter {
    private static final String COMMAND_PARAMETER = "command";
    private static final String ADMIN_PARAMETER = "admin";
    private static final String USER_PARAMETER = "user";
    private static final int FORBIDDEN = 403;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String command = req.getParameter(COMMAND_PARAMETER);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_PARAMETER);
        if (command != null && !isGuestCommand(command)) {
            String rights = getUserRights(user);
            if (ADMIN_PARAMETER.equalsIgnoreCase(rights) && isAdminCommand(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (USER_PARAMETER.equalsIgnoreCase(rights) && isUserCommand(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                resp.sendError(FORBIDDEN);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private String getUserRights(User user) {
        if (user == null) {
            return "guest";
        } else {
            return user.getRights();
        }
    }

    private boolean isGuestCommand(String command) {
        List<String> guestCommands = Arrays.asList(
                "goToLogin",
                "goToFilms",
                "goToFilmHome",
                "login",
                "films",
                "users",
                "film-home",
                "director");
        return guestCommands.contains(command);
    }

    private boolean isUserCommand(String command) {
        List<String> userCommands = Arrays.asList(
                "goToHome",
                "goToReview",
                "goToComment",
                "logout",
                "review-rate",
                "comment",
                "show-reviews",
                "show-comments",

                "goToLogin",
                "goToFilms",
                "goToFilmHome",
                "login",
                "films",
                "users",
                "film-home",
                "director");
        return userCommands.contains(command);
    }

    private boolean isAdminCommand(String command) {
        List<String> adminCommands = Arrays.asList(
                "admin-goToAddFilm",
                "admin-goToEditFilm",
                "admin-user-edit",
                "admin-block-unblock",
                "admin-edit-user-rate",
                "admin-add-film",
                "admin-edit-film",
                "admin-delete-film",
                "admin-delete-comment",
                "admin-delete-review",

                "goToHome",
                "logout",
                "show-reviews",
                "show-comments",

                "goToLogin",
                "goToFilms",
                "goToFilmHome",
                "login",
                "films",
                "users",
                "film-home",
                "director");
        return adminCommands.contains(command);
    }
}