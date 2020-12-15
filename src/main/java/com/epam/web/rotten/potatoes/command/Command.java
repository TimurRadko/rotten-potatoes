package com.epam.web.rotten.potatoes.command;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;

public interface Command {
    CommandResult execute(RequestContext requestContext) throws ServiceException;
}
