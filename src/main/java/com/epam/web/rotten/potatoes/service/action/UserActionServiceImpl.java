package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.dao.impl.user.UserDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class UserActionServiceImpl implements UserActionService {
    private static final Logger LOGGER = LogManager.getLogger(DaoHelper.class);
    private static final int COUNT_USER_ACTION_CHANGING = 2;
    private static final int HALF_MAX_RATE_FILM = 5;
    private DaoHelperFactory daoHelperFactory;

    public UserActionServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void addReviewAndRate(UserAction userAction) throws ServiceException {
        DaoHelper daoHelper = null;
        try {
            daoHelper = daoHelperFactory.create();
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            daoHelper.startTransaction();
            userActionDao.save(userAction);
            changeUserRate(userAction, daoHelper);
            daoHelper.endTransaction();
        } catch (DaoException e) {
            try {
                daoHelper.rollback();
            } catch (DaoException rollbackException) {
                throw new ServiceException(rollbackException);
            }
            throw new ServiceException(e);
        } finally {
            if (daoHelper != null) {
                try {
                    daoHelper.setAutoCommit(true);
                    daoHelper.close();
                } catch (DaoException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    private void changeUserRate(UserAction userAction, DaoHelper daoHelper) throws ServiceException, DaoException {
        int filmId = userAction.getFilmId();
        List<UserAction> reviews = getReviewsByFilmId(filmId);
        if (reviews.size() > COUNT_USER_ACTION_CHANGING) {
            int userRate = userAction.getFilmRate();
            int adjustNumberRate = HALF_MAX_RATE_FILM - Math.abs(userRate - getRoundAvgRate(reviews));

            UserDao userDao = daoHelper.createUserDao();
            int userId = userAction.getUserId();
            Optional<User> optionalUser = userDao.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                int newUserRate = user.getRate() + adjustNumberRate;
                if (newUserRate < 0) {
                    userDao.save(user);
                } else {
                    userDao.save(new User(user, newUserRate));
                }
            }
        }
    }

    private int getRoundAvgRate(List<UserAction> reviews) {
        double sumFilmRate = 0;
        for (UserAction review : reviews) {
            sumFilmRate += review.getFilmRate();
        }
        double avgRate = sumFilmRate / reviews.size();
        return (int) Math.round(avgRate);
    }

    @Override
    public List<UserAction> getReviewsByFilmId(Integer filmId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            return userActionDao.findReviewsByFilmId(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserAction> getReviewsByUserId(Integer userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            return userActionDao.findReviewsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            userActionDao.remove(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<UserAction> getReviewById(Integer reviewId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            return userActionDao.findById(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}