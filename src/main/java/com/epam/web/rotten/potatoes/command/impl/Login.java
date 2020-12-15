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

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String RIGHTS_PARAMETER = "rights";
    private static final String RATE_PARAMETER = "rate";
    private static final String LOGIN_PAGE = "WEB-INF/views/login.jsp";
    private static final String MAIN_PAGE = "/rotten-potatoes/controller?command=goToMain";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MESSAGE_VALUE = "error";

    public Login(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String login = requestContext.getRequestParameter(LOGIN_PARAMETER);
        String password = requestContext.getRequestParameter(PASSWORD_PARAMETER);

        Optional<User> user = userServiceImpl.login(login, password);

        if (user.isPresent()) {
            User sessionUser = user.get();
            setSessionUserData(requestContext, sessionUser);
            return CommandResult.redirect(MAIN_PAGE);
        } else {
            requestContext.setSessionAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE_VALUE);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

    private void setSessionUserData(RequestContext requestContext, User sessionUser) {
        String login = sessionUser.getLogin();
        Rights rights = sessionUser.getRights();
        double rate = sessionUser.getRate();
        requestContext.setSessionAttribute(LOGIN_PARAMETER, login);
        requestContext.setSessionAttribute(RIGHTS_PARAMETER, rights);
        requestContext.setSessionAttribute(RATE_PARAMETER, rate);
    }
}
