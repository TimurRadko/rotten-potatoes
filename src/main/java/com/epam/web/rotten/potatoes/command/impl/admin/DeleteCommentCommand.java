package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserComment;
import com.epam.web.rotten.potatoes.service.comment.UserCommentService;

import java.util.Optional;

public class DeleteCommentCommand implements Command {
    private final UserCommentService userCommentService;
    private static final String ID_PARAMETER = "id";
    private static final String INDEX_PAGE = "index.jsp";

    public DeleteCommentCommand(UserCommentService userCommentService) {
        this.userCommentService = userCommentService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        String stringCommentId = requestContext.getRequestParameter(ID_PARAMETER);
        if (stringCommentId == null) {
            throw new ServiceException("Incoming parameters are: null");
        }

        int commentId = Integer.parseInt(stringCommentId);
        Optional<UserComment> optionalUserComment = userCommentService.findCommentById(commentId);
        if (optionalUserComment.isPresent()) {
            userCommentService.remove(commentId);
        }
        return CommandResult.redirect(INDEX_PAGE);
    }
}