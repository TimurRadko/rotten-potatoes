package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;
import org.junit.Assert;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class UserActionServiceImplTest {
    private static final int USER_ACTION_ID_VALUE = 1;
    private static final int FILM_RATE_VALUE = 10;
    private static final String REVIEW_VALUE = "Hello";
    private static final int USER_ID_VALUE = 1;
    private static final int FILM_ID_VALUE = 1;
    private static final UserAction USER_ACTION =
            new UserAction(USER_ACTION_ID_VALUE, FILM_RATE_VALUE,
                    REVIEW_VALUE, USER_ID_VALUE, FILM_ID_VALUE);

    private static final int FILM_ID_FOR_FINDING = 1;
    private static final int USER_ID_FOR_FINDING = 1;
    private static final int ID_FOR_FINDING = 1;
    private static final List<UserAction> EXPECTED_USER_ACTION = Collections.singletonList(USER_ACTION);

    @Test(expectedExceptions = ServiceException.class)//then
    public void testAddReviewAndRateShouldThrowServiceExceptionWhenMethodThrowDaoException() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserActionDao userActionDao = Mockito.mock(UserActionDao.class);
        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserActionDao()).thenReturn(userActionDao);

        UserActionService userActionService = new UserActionServiceImpl(daoHelperFactory);
        doThrow(DaoException.class).when(userActionDao).save(any());

        userActionService.addReviewAndRate(USER_ACTION);
    }

    @Test
    public void testGetReviewByFilmIdShouldReturnUserActionListWhenItsExists() throws ServiceException, DaoException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserActionDao userActionDao = Mockito.mock(UserActionDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserActionDao()).thenReturn(userActionDao);
        when(userActionDao.findReviewsByFilmId(FILM_ID_FOR_FINDING)).thenReturn(EXPECTED_USER_ACTION);
        UserActionService userActionService = new UserActionServiceImpl(daoHelperFactory);

        //then
        List<UserAction> actualUserActions = userActionService.getReviewsByFilmId(FILM_ID_FOR_FINDING);
        Assert.assertEquals(EXPECTED_USER_ACTION, actualUserActions);
    }

    @Test
    public void testGetReviewByFilmIdShouldReturnEmptyListWhenItsNotExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserActionDao userActionDao = Mockito.mock(UserActionDao.class);
        List<UserAction> emptyUserActions = new ArrayList<>();

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserActionDao()).thenReturn(userActionDao);
        when(userActionDao.findReviewsByFilmId(FILM_ID_FOR_FINDING)).thenReturn(emptyUserActions);
        UserActionService userActionService = new UserActionServiceImpl(daoHelperFactory);

        //then
        List<UserAction> actualUserActions = userActionService.getReviewsByFilmId(FILM_ID_FOR_FINDING);
        Assert.assertEquals(0, actualUserActions.size());
    }

    @Test
    public void testGetReviewByUserIdShouldReturnUserActionListWhenItsExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserActionDao userActionDao = Mockito.mock(UserActionDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserActionDao()).thenReturn(userActionDao);
        when(userActionDao.findReviewsByUserId(USER_ID_FOR_FINDING)).thenReturn(EXPECTED_USER_ACTION);
        UserActionService userActionService = new UserActionServiceImpl(daoHelperFactory);

        //then
        List<UserAction> actualUserActions = userActionService.getReviewsByUserId(USER_ID_FOR_FINDING);
        Assert.assertEquals(EXPECTED_USER_ACTION, actualUserActions);
    }

    @Test
    public void testGetReviewByUserIdShouldReturnEmptyListWhenItsNotExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserActionDao userActionDao = Mockito.mock(UserActionDao.class);
        List<UserAction> emptyUserActions = new ArrayList<>();

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserActionDao()).thenReturn(userActionDao);
        when(userActionDao.findReviewsByUserId(USER_ID_FOR_FINDING)).thenReturn(emptyUserActions);
        UserActionService userActionService = new UserActionServiceImpl(daoHelperFactory);

        //then
        List<UserAction> actualUserActions = userActionService.getReviewsByUserId(USER_ID_FOR_FINDING);
        Assert.assertEquals(0, actualUserActions.size());
    }

    @Test
    public void testGetReviewByIdShouldReturnUserActionWhenItExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserActionDao userActionDao = Mockito.mock(UserActionDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserActionDao()).thenReturn(userActionDao);
        when(userActionDao.findById(ID_FOR_FINDING)).thenReturn(Optional.of(USER_ACTION));
        UserActionService userActionService = new UserActionServiceImpl(daoHelperFactory);

        //then
        Optional<UserAction> actualUserAction = userActionService.getReviewById(ID_FOR_FINDING);
        Assert.assertEquals(Optional.of(USER_ACTION), actualUserAction);
    }

    @Test
    public void testGetReviewByIdShouldReturnEmptyOptionalWhenItNotExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserActionDao userActionDao = Mockito.mock(UserActionDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserActionDao()).thenReturn(userActionDao);
        when(userActionDao.findById(ID_FOR_FINDING)).thenReturn(Optional.empty());
        UserActionService userActionService = new UserActionServiceImpl(daoHelperFactory);

        //then
        Optional<UserAction> actualUserAction = userActionService.getReviewById(ID_FOR_FINDING);
        Assert.assertEquals(Optional.empty(), actualUserAction);
    }
}