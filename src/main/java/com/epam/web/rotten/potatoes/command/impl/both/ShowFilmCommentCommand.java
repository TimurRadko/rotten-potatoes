package com.epam.web.rotten.potatoes.command.impl.both;

import com.epam.web.rotten.potatoes.command.Command;
import com.epam.web.rotten.potatoes.command.CommandResult;
import com.epam.web.rotten.potatoes.controller.context.RequestContext;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserComment;
import com.epam.web.rotten.potatoes.model.dto.UserCommentDto;
import com.epam.web.rotten.potatoes.service.comment.UserCommentService;
import com.epam.web.rotten.potatoes.service.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowFilmCommentCommand implements Command {
    private final UserCommentService userCommentService;
    private final UserService userService;

    private static final String USER_COMMENTS = "user_comments";
    private static final String FILM = "film";
    private static final String FILM_HOME_PAGE = "WEB-INF/views/film-home.jsp";

    public ShowFilmCommentCommand(UserCommentService userCommentService, UserService userService) {
        this.userCommentService = userCommentService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        Film film = (Film) requestContext.getSessionAttribute(FILM);
        if (film == null) {
            throw new ServiceException("Incoming parameters are: null");
        }
        int filmId = film.getId();
        List<UserComment> comments = userCommentService.getCommentsByFilmId(filmId);
        List<UserCommentDto> usersAndReviews = getAllComments(comments);
        requestContext.setRequestAttribute(USER_COMMENTS, usersAndReviews);
        return CommandResult.forward(FILM_HOME_PAGE);
    }

    private List<UserCommentDto> getAllComments(List<UserComment> comments) throws ServiceException {
        List<UserCommentDto> userReviewDtos = new ArrayList<>();

        for (UserComment comment : comments) {
            Integer userId = comment.getUserId();
            Integer commentId = comment.getId();
            String userComment = comment.getComment();
            Optional<User> optionalUser = userService.getUserById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String login = user.getLogin();
                UserCommentDto userCommentDto = new UserCommentDto(commentId, login, userComment);
                userReviewDtos.add(userCommentDto);
            }
        }
        return userReviewDtos;
    }
}