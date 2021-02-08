package com.epam.web.rotten.potatoes.command.impl.both;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

import java.util.Optional;

public class GetUserByIdCommand implements Command {
    private final UserService userService;
    private static final String ID_PARAMETER = "id";
    private static final String USER_PARAMETER = "user";
    private static final String USER_EDIT_PAGE = "WEB-INF/views/user-edit.jsp";
    private static final String FILMS_PAGE = "WEB-INF/views/films.jsp";
    private static final String INCOMING_PARAMETER_ARE_NULL = "Incoming parameters are: null";

    public GetUserByIdCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringUserId = requestContext.getRequestParameter(ID_PARAMETER);
        if (stringUserId == null) {
            throw new ServiceException(INCOMING_PARAMETER_ARE_NULL);
        }

        int userId = Integer.parseInt(stringUserId);
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            requestContext.setRequestAttribute(USER_PARAMETER, user);
            return CommandResult.forward(USER_EDIT_PAGE);
        } else {
            return CommandResult.forward(FILMS_PAGE);
        }
    }
}