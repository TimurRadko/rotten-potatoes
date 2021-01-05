package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;

import java.util.Optional;

public class DeleteReview implements Command {
    private final UserActionService userActionService;
    private static final String ID_PARAMETER = "id";
    private static final String INDEX_PAGE = "index.jsp";

    public DeleteReview(UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringReviewId = requestContext.getRequestParameter(ID_PARAMETER);
        int commentId = Integer.parseInt(stringReviewId);
        Optional<UserAction> optionalUserAction = userActionService.findReviewById(commentId);
        if (optionalUserAction.isPresent()) {
            userActionService.remove(commentId);
        }
        return CommandResult.redirect(INDEX_PAGE);
    }
}
