package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

public class GoToPageCommand implements Command {
    private final String page;

    public GoToPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) {
        return CommandResult.forward(page);
    }
}