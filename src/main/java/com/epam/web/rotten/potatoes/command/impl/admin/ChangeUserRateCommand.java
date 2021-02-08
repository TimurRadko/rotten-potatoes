package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.Optional;

public class ChangeUserRateCommand implements Command {
    private final UserService userService;
    private static final String ID_PARAMETER = "id";
    private static final String RATE_PARAMETER = "rate";
    private static final String USERS_PAGE_COMMAND = "/rotten-potatoes/controller?command=users";
    private static final String USER_EDIT_PAGE = "WEB-INF/views/user-edit.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_ENTER_RATE = "errorRate";
    private static final String ERROR_NEGATIVE_RATE = "negativeRate";
    private static final String USER_PARAMETER = "user";
    private static final String INCOMING_PARAMETERS_ARE_NULL = "Incoming parameters are: null";

    public ChangeUserRateCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringUserId = requestContext.getRequestParameter(ID_PARAMETER);
        if (stringUserId == null) {
            throw new ServiceException(INCOMING_PARAMETERS_ARE_NULL);
        }

        Integer userId = Integer.parseInt(stringUserId);
        String stringRate = requestContext.getRequestParameter(RATE_PARAMETER);

        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            int newRate;
            try {
                newRate = Integer.parseInt(stringRate);
            } catch (NumberFormatException e) {
                requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_ENTER_RATE);
                requestContext.setRequestAttribute(USER_PARAMETER, user);
                return CommandResult.forward(USER_EDIT_PAGE);
            }
            if (newRate > 0) {
                user = new User(user, newRate);
                userService.save(user);
                return CommandResult.redirect(USERS_PAGE_COMMAND);
            } else {
                requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_NEGATIVE_RATE);
                requestContext.setRequestAttribute(USER_PARAMETER, user);
                return CommandResult.forward(USER_EDIT_PAGE);
            }
        }
        return CommandResult.forward(USER_EDIT_PAGE);
    }
}