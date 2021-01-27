package com.epam.web.rotten.potatoes.command;

import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;

/**
 * The interface describes any action performed on the server side of the application.
 */
public interface Command {
    /**
     * Performs the necessary actions using the parameters of RequestContext object
     * and returns CommandResult object with further page transition instructions
     *
     * @param requestContext - passed into the method requestContext, which is contained all request parameters
     *                       and attributes and also session attributes
     * @return CommandResult - return commandResult forward or redirect
     * @throws ServiceException in case of errors
     */
    CommandResult execute(RequestContext requestContext) throws ServiceException;
}