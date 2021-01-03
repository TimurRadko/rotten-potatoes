package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.Optional;

public class ChangeUserRate implements Command {
    private final UserService userService;
    private static final String ID_PARAMETER = "id";
    private static final String RATE_PARAMETER = "rate";
    private static final String USERS_PAGE = "/rotten-potatoes/controller?command=users";
    private static final String USER_EDIT_PAGE = "WEB-INF/views/user-edit.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_ENTER_RATE = "errorRate";
    private static final String NEGATIVE_RATE = "negativeRate";

    public ChangeUserRate(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringUserId = requestContext.getRequestParameter(ID_PARAMETER);

        String stringRate = requestContext.getRequestParameter(RATE_PARAMETER);
        int newRate;
        try {
            newRate = Integer.parseInt(stringRate);
        } catch (NumberFormatException e) {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_ENTER_RATE);
            return CommandResult.forward(USER_EDIT_PAGE);
        }

        if (newRate > 0) {
            int userId = Integer.parseInt(stringUserId);
            Optional<User> optionalUser = userService.getUserById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user = getNewRateUser(user, newRate);
                userService.changeUserData(user);
            }
            return CommandResult.redirect(USERS_PAGE);
        } else {
            requestContext.setRequestAttribute(ERROR_MESSAGE_ATTRIBUTE, NEGATIVE_RATE);
            return CommandResult.forward(USER_EDIT_PAGE);
        }
    }

    private User getNewRateUser(User user, int newRate) {
        int userId = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        String rights = user.getRights();
        boolean isBlocked = user.isBlocked();
        return new User(userId, login, password, rights, newRate, isBlocked);
    }
}
