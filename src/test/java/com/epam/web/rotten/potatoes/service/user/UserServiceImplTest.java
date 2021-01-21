package com.epam.web.rotten.potatoes.service.user;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.user.UserDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private static final int FIRST_USER_ID_VALUE = 1;
    private static final String FIRST_LOGIN_VALUE = "Valid Login";
    private static final String FIRST_PASSWORD_VALUE = "Valid Password";
    private static final String FIRST_RIGHTS_VALUE = "user";
    private static final int FIRST_RATE_VALUE = 50;
    private static final User FIRST_USER = new User(FIRST_USER_ID_VALUE, FIRST_LOGIN_VALUE,
            FIRST_RIGHTS_VALUE, FIRST_RATE_VALUE, false);

    private static final int SECOND_USER_ID_VALUE = 2;
    private static final int SECOND_RATE_VALUE = 60;
    private static final User SECOND_USER = new User(SECOND_USER_ID_VALUE, FIRST_LOGIN_VALUE,
            FIRST_RIGHTS_VALUE, SECOND_RATE_VALUE, false);

    private static final List<User> SORTED_LIST = Arrays.asList(FIRST_USER, SECOND_USER);

    @Test
    public void testLoginShouldReturnUserWhenDbContainedUser() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(userDao.findUserByLoginAndId(FIRST_LOGIN_VALUE, FIRST_PASSWORD_VALUE)).thenReturn(Optional.of(FIRST_USER));
        UserService userService = new UserServiceImpl(daoHelperFactory);

        Optional<User> actualUser = userService.login(FIRST_LOGIN_VALUE, FIRST_PASSWORD_VALUE);
        Assert.assertEquals(Optional.of(FIRST_USER), actualUser);
    }

    @Test
    public void testLoginShouldReturnEmptyOptionalWhenDbDoNotContainedUser() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(userDao.findUserByLoginAndId(FIRST_LOGIN_VALUE, FIRST_PASSWORD_VALUE)).thenReturn(Optional.empty());
        UserService userService = new UserServiceImpl(daoHelperFactory);

        //then
        Optional<User> actualUser = userService.login(FIRST_LOGIN_VALUE, FIRST_PASSWORD_VALUE);
        Assert.assertEquals(Optional.empty(), actualUser);
    }

    @Test
    public void testGetUserByIdShouldReturnUserWhenDbContainedUser() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(userDao.findById(FIRST_USER_ID_VALUE)).thenReturn(Optional.of(FIRST_USER));
        UserService userService = new UserServiceImpl(daoHelperFactory);

        //then
        Optional<User> actualUser = userService.getUserById(FIRST_USER_ID_VALUE);
        Assert.assertEquals(Optional.of(FIRST_USER), actualUser);
    }

    @Test
    public void testGetUserByIdShouldReturnEmptyOptionalWhenDbDoNotContainedUser() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(userDao.findById(FIRST_USER_ID_VALUE)).thenReturn(Optional.empty());
        UserService userService = new UserServiceImpl(daoHelperFactory);

        //then
        Optional<User> actualUser = userService.getUserById(FIRST_USER_ID_VALUE);
        Assert.assertEquals(Optional.empty(), actualUser);
    }

    @Test
    public void testSaveShouldReturnIdWhenFilmIsSaved() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(userDao.save(FIRST_USER)).thenReturn(Optional.of(FIRST_USER_ID_VALUE));
        UserService userService = new UserServiceImpl(daoHelperFactory);

        //then
        Optional<Integer> actualUserId = userService.save(FIRST_USER);
        Assert.assertEquals(Optional.of(FIRST_USER_ID_VALUE), actualUserId);
    }

    @Test
    public void testGetTopUsersShouldReturnUsersListSortedByRate() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(userDao.findTopUsers()).thenReturn(SORTED_LIST);
        UserService userService = new UserServiceImpl(daoHelperFactory);

        //then
        List<User> actualSortedList = userService.getTopUsers();
        Assert.assertEquals(SORTED_LIST, actualSortedList);
    }
}