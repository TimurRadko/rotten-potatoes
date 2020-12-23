package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

public class Logout implements Command {
    private static final String ID_PARAMETER = "id";
    private static final String LOGIN_PARAMETER = "login";
    private static final String RIGHTS_PARAMETERS = "rights";
    private static final String RATE_PARAMETER = "rate";
    private static final String BLOCKED_PARAMETER = "blocked";
    private static final String FILMS_PAGE = "/rotten-potatoes/controller?command=films";

    @Override
    public CommandResult execute(RequestContext requestContext) {
        requestContext.setSessionAttribute(ID_PARAMETER, null);
        requestContext.setSessionAttribute(LOGIN_PARAMETER, null);
        requestContext.setSessionAttribute(RIGHTS_PARAMETERS, null);
        requestContext.setSessionAttribute(RATE_PARAMETER, null);
        requestContext.setSessionAttribute(BLOCKED_PARAMETER, null);
        return CommandResult.redirect(FILMS_PAGE);
    }

}
