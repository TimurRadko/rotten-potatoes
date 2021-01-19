package com.epam.web.rotten.potatoes.command;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;

public interface Command {
    /**
     * @param requestContext - passed into the method requestContext, which is contained all request parameters
     *                       and attributes and also session attributes
     * @return CommandResult - return commandResult forward or redirect
     * @throws ServiceException
     */
    CommandResult execute(RequestContext requestContext) throws ServiceException;
}
