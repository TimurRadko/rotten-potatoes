package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

public class Logout implements Command {
    private static final String FILMS_PAGE_COMMAND = "/rotten-potatoes/controller?command=films";
    private static final String INVALIDATE_ATTRIBUTE = "invalidate";

    @Override
    public CommandResult execute(RequestContext requestContext) {
        requestContext.setSessionAttribute(INVALIDATE_ATTRIBUTE, true);
        return CommandResult.redirect(FILMS_PAGE_COMMAND);
    }

}
