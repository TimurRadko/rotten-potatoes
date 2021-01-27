package com.epam.web.rotten.potatoes.service.comment;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.comment.UserCommentDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserComment;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class UserCommentServiceImplTest {
    private static final int USER_COMMENT_ID_VALUE = 1;
    private static final String COMMENT_VALUE = "Hello";
    private static final int USER_ID_VALUE = 1;
    private static final int FILM_ID_VALUE = 1;
    private static final UserComment USER_COMMENT =
            new UserComment(USER_COMMENT_ID_VALUE, COMMENT_VALUE,
                    USER_ID_VALUE, FILM_ID_VALUE);

    private static final int FILM_ID_FOR_FINDING = 1;
    private static final int USER_ID_FOR_FINDING = 1;
    private static final int ID_FOR_FINDING = 1;
    private static final List<UserComment> EXPECTED_USER_COMMENT = Collections.singletonList(USER_COMMENT);

    @Test(expectedExceptions = ServiceException.class)//then
    public void testAddCommentShouldThrowServiceExceptionWhenMethodThrowDaoException() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserCommentDao userCommentDao = Mockito.mock(UserCommentDao.class);
        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserCommentDao()).thenReturn(userCommentDao);

        UserCommentService userCommentService = new UserCommentServiceImpl(daoHelperFactory);
        doThrow(DaoException.class).when(userCommentDao).save(any());

        userCommentService.addComment(USER_COMMENT);
    }

    @Test
    public void testGetReviewByFilmIdShouldReturnUserActionListWhenItsExists() throws ServiceException, DaoException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserCommentDao userCommentDao = Mockito.mock(UserCommentDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserCommentDao()).thenReturn(userCommentDao);
        when(userCommentDao.findCommentsByFilmId(FILM_ID_FOR_FINDING)).thenReturn(EXPECTED_USER_COMMENT);
        UserCommentService userCommentService = new UserCommentServiceImpl(daoHelperFactory);

        //then
        List<UserComment> actualUserComments = userCommentService.getCommentsByFilmId(FILM_ID_FOR_FINDING);
        Assert.assertEquals(EXPECTED_USER_COMMENT, actualUserComments);
    }

    @Test
    public void testGetCommentByFilmIdShouldReturnEmptyListWhenItsNotExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserCommentDao userCommentDao = Mockito.mock(UserCommentDao.class);
        List<UserComment> emptyUserComments = new ArrayList<>();

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserCommentDao()).thenReturn(userCommentDao);
        when(userCommentDao.findCommentsByFilmId(FILM_ID_FOR_FINDING)).thenReturn(emptyUserComments);
        UserCommentService userCommentService = new UserCommentServiceImpl(daoHelperFactory);

        //then
        List<UserComment> actualCommentActions = userCommentService.getCommentsByFilmId(FILM_ID_FOR_FINDING);
        Assert.assertEquals(0, actualCommentActions.size());
    }

    @Test
    public void testGetReviewByUserIdShouldReturnUserActionListWhenItsExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserCommentDao userCommentDao = Mockito.mock(UserCommentDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserCommentDao()).thenReturn(userCommentDao);
        when(userCommentDao.findCommentsByUserId(USER_ID_FOR_FINDING)).thenReturn(EXPECTED_USER_COMMENT);
        UserCommentService userCommentService = new UserCommentServiceImpl(daoHelperFactory);

        //then
        List<UserComment> actualUserComments = userCommentService.getCommentsByUserId(USER_ID_FOR_FINDING);
        Assert.assertEquals(EXPECTED_USER_COMMENT, actualUserComments);
    }

    @Test
    public void testGetReviewByUserIdShouldReturnEmptyListWhenItsNotExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserCommentDao userCommentDao = Mockito.mock(UserCommentDao.class);
        List<UserComment> emptyUserComment = new ArrayList<>();

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserCommentDao()).thenReturn(userCommentDao);
        when(userCommentDao.findCommentsByUserId(USER_ID_FOR_FINDING)).thenReturn(emptyUserComment);
        UserCommentService userCommentService = new UserCommentServiceImpl(daoHelperFactory);

        //then
        List<UserComment> actualUserComments = userCommentService.getCommentsByUserId(USER_ID_FOR_FINDING);
        Assert.assertEquals(0, actualUserComments.size());
    }

    @Test
    public void testGetReviewByIdShouldReturnUserActionWhenItExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserCommentDao userCommentDao = Mockito.mock(UserCommentDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserCommentDao()).thenReturn(userCommentDao);
        when(userCommentDao.findById(ID_FOR_FINDING)).thenReturn(Optional.of(USER_COMMENT));
        UserCommentService userCommentService = new UserCommentServiceImpl(daoHelperFactory);

        //then
        Optional<UserComment> actualUserComment = userCommentService.getCommentById(ID_FOR_FINDING);
        Assert.assertEquals(Optional.of(USER_COMMENT), actualUserComment);
    }

    @Test
    public void testGetReviewByIdShouldReturnEmptyOptionalWhenItNotExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserCommentDao userCommentDao = Mockito.mock(UserCommentDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserCommentDao()).thenReturn(userCommentDao);
        when(userCommentDao.findById(ID_FOR_FINDING)).thenReturn(Optional.empty());
        UserCommentService userCommentService = new UserCommentServiceImpl(daoHelperFactory);

        //then
        Optional<UserComment> actualUserComment = userCommentService.getCommentById(ID_FOR_FINDING);
        Assert.assertEquals(Optional.empty(), actualUserComment);
    }
}