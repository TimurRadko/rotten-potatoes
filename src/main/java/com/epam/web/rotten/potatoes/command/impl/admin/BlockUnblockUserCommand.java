package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.Optional;

public class BlockUnblockUserCommand implements Command {
    private final UserService userService;
    private static final String ID_PARAMETER = "id";
    private static final String USERS_PAGE_COMMAND = "/rotten-potatoes/controller?command=users";

    public BlockUnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringUserId = requestContext.getRequestParameter(ID_PARAMETER);
        Integer userId = Integer.parseInt(stringUserId);
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User newUser = getBlockedUnblockedUser(user);
            userService.blockUnblockUser(newUser);
        }
        return CommandResult.redirect(USERS_PAGE_COMMAND);
    }

    private User getBlockedUnblockedUser(User user) {
        int userId = user.getId();
        String login = user.getLogin();
        String rights = user.getRights();
        int rate = user.getRate();
        boolean isBlocked = user.isBlocked();
        return new User(userId, login, rights, rate, !isBlocked);
    }
}