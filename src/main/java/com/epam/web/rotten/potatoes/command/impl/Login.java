package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Rights;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserServiceImpl;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.Optional;

public class Login implements Command {
    private final UserServiceImpl userServiceImpl;

    private static final String ID_PARAMETER = "user_id";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String RIGHTS_PARAMETER = "rights";
    private static final String RATE_PARAMETER = "rate";
    private static final String BLOCKED_PARAMETER = "blocked";
    private static final String LOGIN_PAGE = "WEB-INF/views/login.jsp";
    private static final String FILMS_PAGE = "/rotten-potatoes/controller?command=films";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String ERROR_USER_BLOCKED = "errorBlocked";

    public Login(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String login = requestContext.getRequestParameter(LOGIN_PARAMETER);
        String password = requestContext.getRequestParameter(PASSWORD_PARAMETER);

        Optional<User> optionalUser = userServiceImpl.login(login, password);

        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_LOGIN);
            return CommandResult.forward(LOGIN_PAGE);
        }
        if (user.isBlocked()) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_USER_BLOCKED);
            return CommandResult.forward(LOGIN_PAGE);
        }
        setSessionUserData(requestContext, user);
        return CommandResult.redirect(FILMS_PAGE);
    }

    private void setSessionUserData(RequestContext requestContext, User user) {
        int id = user.getId();
        String login = user.getLogin();
        Rights rights = user.getRights();
        double rate = user.getRate();
        boolean blocked = user.isBlocked();
        requestContext.setSessionAttribute(ID_PARAMETER, id);
        requestContext.setSessionAttribute(LOGIN_PARAMETER, login);
        requestContext.setSessionAttribute(RIGHTS_PARAMETER, rights);
        requestContext.setSessionAttribute(RATE_PARAMETER, rate);
        requestContext.setSessionAttribute(BLOCKED_PARAMETER, blocked);
    }
}
