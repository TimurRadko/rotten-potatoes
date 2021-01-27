package com.epam.web.rotten.potatoes.command.impl.admin;

import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserComment;
import com.epam.web.rotten.potatoes.service.comment.UserCommentService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class DeleteCommentCommandTest {
    private static final String ID_PARAMETER = "id";
    private static final String ID_VALUE = "1";
    private static final int USER_COMMENT_ID_VALUE = 1;
    private static final String USER_COMMENT_VALUE = "Valid Comment";
    private static final int FILM_ID_VALUE = 1;
    private static final int USER_ID_VALUE = 1;
    private static final UserComment USER_COMMENT = new UserComment(USER_COMMENT_ID_VALUE, USER_COMMENT_VALUE,
            FILM_ID_VALUE, USER_ID_VALUE);
    private static final String INDEX_PAGE = "index.jsp";

    @Test
    public void testExecuteShouldReturnRedirectWhenDeletingIsSuccessful() throws ServiceException {
        //given
        UserCommentService userCommentService = Mockito.mock(UserCommentService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, ID_VALUE);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        DeleteCommentCommand deleteComment = new DeleteCommentCommand(userCommentService);
        //when
        when(userCommentService.getCommentById(anyInt())).thenReturn(Optional.of(USER_COMMENT));
        CommandResult actual = deleteComment.execute(context);
        //then
        CommandResult expected = CommandResult.redirect(INDEX_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)//then
    public void testExecuteShouldThrowServiceExceptionWhenIdParameterEqualsNull() throws ServiceException {
        //given
        UserCommentService userCommentService = Mockito.mock(UserCommentService.class);
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(ID_PARAMETER, null);
        RequestContext context = new RequestContext(new HashMap<>(), requestParameters, new HashMap<>());
        DeleteCommentCommand deleteComment = new DeleteCommentCommand(userCommentService);
        //then
        deleteComment.execute(context);
    }
}