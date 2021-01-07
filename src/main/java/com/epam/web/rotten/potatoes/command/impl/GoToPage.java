package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;

public class GoToPage implements Command {
    private final String page;

    public GoToPage(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) {
        return CommandResult.forward(page);
    }
}