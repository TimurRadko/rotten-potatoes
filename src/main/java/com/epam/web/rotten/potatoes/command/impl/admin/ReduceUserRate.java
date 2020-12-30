package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.Optional;

public class ReduceUserRate implements Command {
    private final UserService userService;
    private static final String ID_PARAMETER = "id";
    private static final String USERS_PAGE = "/rotten-potatoes/controller?command=users";

    public ReduceUserRate(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringUserId = requestContext.getRequestParameter(ID_PARAMETER);
        int userId = Integer.parseInt(stringUserId);
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String login = user.getLogin();
            String password = user.getPassword();
            String rights = user.getRights();
            boolean isBlocked = user.isBlocked();
            int rate = user.getRate();
            int newRate = rate - 10;
            if (newRate < 0) {
               newRate = rate;
            }
            userService.changeUserData(new User(userId, login, password, rights, newRate, isBlocked));
        }
        return CommandResult.redirect(USERS_PAGE);
    }
}
