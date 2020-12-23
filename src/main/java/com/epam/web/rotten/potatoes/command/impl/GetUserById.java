package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.Optional;

public class GetUserById implements Command {
    private final UserService userService;
    private static final String ID_PARAMETER = "id";
    private static final String USER_PARAMETER = "user";
    private static final String USER_EDIT_PAGE = "WEB-INF/views/user-edit.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";

    public GetUserById(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int id = Integer.parseInt(requestContext.getRequestParameter(ID_PARAMETER));
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User sessionUser = user.get();
            requestContext.setSessionAttribute(USER_PARAMETER, sessionUser);
            return CommandResult.forward(USER_EDIT_PAGE);
        } else {
            return CommandResult.forward(FILMS_PAGE);
        }
    }
}
