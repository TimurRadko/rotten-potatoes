package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.dao.impl.user.UserDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.User;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.*;

public class UserActionServiceImpl implements UserActionService {
    private DaoHelperFactory daoHelperFactory;

    public UserActionServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void addReviewAndRate(UserAction userAction) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            Optional<Integer> optionalId = userActionDao.save(userAction);
            if (optionalId.isPresent()) {
                int id = optionalId.get();
                changeUserRate(daoHelper, id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void changeUserRate(DaoHelper daoHelper, int id) throws ServiceException {
        Optional<UserAction> optionalReview = findReviewById(id);
        if (optionalReview.isPresent()) {
            UserAction action = optionalReview.get();
            int filmId = action.getFilmId();
            int userRate = action.getFilmRate();
            int userId = action.getUserId();
            List<UserAction> reviews = findReviewsByFilmId(filmId);
            if (reviews.size() > 3) {
                double avgRate = getAvgRate(reviews, id);
                int filmRate = (int) Math.round(avgRate);
                int adjustNumberRate = 5 - Math.abs(userRate - filmRate);
                getUserWithNewRate(daoHelper, userId, adjustNumberRate);
            }
        }
    }

    private double getAvgRate(List<UserAction> reviews, int id) {
        double sumFilmRate = 0;
        for (UserAction review : reviews) {
            if (review.getId() != id) {
                sumFilmRate += review.getFilmRate();
            }
        }
        return sumFilmRate / (reviews.size() - 1);
    }

    private void getUserWithNewRate(DaoHelper daoHelper, int userId, int adjustNumberRate) throws ServiceException {
        try {
            UserDao userDao = daoHelper.createUserDao();
            Optional<User> optionalUser = userDao.getById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String login = user.getLogin();
                String rights = user.getRights();
                boolean blocked = user.isBlocked();
                int currentUserRate = user.getRate();
                int newUserRate = currentUserRate + adjustNumberRate;
                if (newUserRate < 0) {
                    userDao.save(user);
                } else {
                    userDao.save(new User(userId, login, rights, newUserRate, blocked));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserAction> findReviewsByFilmId(Integer filmId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            return userActionDao.getReviewsByFilmId(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserAction> findReviewsByUserId(Integer userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            return userActionDao.getReviewsByUserId(userId);
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
    public Optional<UserAction> findReviewById(Integer reviewId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            return userActionDao.getById(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}