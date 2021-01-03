package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.dao.impl.film.FilmDao;
import com.epam.web.rotten.potatoes.dao.impl.user.UserDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
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
        DaoHelper daoHelper = null;
        try {
            daoHelper = daoHelperFactory.create();
            daoHelper.startTransaction();
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            userActionDao.save(userAction);
            saveNewFilmAndUserParameters(userAction, daoHelper);
            daoHelper.endTransaction();
        } catch (DaoException e) {
            daoHelper.rollback();
            throw new ServiceException(e.getMessage(), e);
        } finally {
            if (daoHelper != null) {
                daoHelper.close();
            }
        }
    }

    private void saveNewFilmAndUserParameters(UserAction userAction, DaoHelper daoHelper) throws DaoException, ServiceException {

        int userRate = userAction.getFilmRate();
        int userId = userAction.getUserId();
        int filmId = userAction.getFilmId();

        int adjustNumberRate;
        FilmDao filmDao = daoHelper.createFilmDao();
        Optional<Film> findFilm = filmDao.getById(filmId);
        if (findFilm.isPresent()) {
            Film film = findFilm.get();
            Film newFilm = getFilmWithNewRate(film, userRate);
            filmDao.save(newFilm);
            double currentRate = film.getAvgRate();
            List<UserAction> reviews = findReviewsByFilmId(filmId);
            if (reviews.size() > 3) {
                int filmRate = (int) Math.round(currentRate);
                adjustNumberRate = 5 - Math.abs(userRate - filmRate);
                getUserWithNewRate(daoHelper, userId, adjustNumberRate);
            }
        }
    }

    private Film getFilmWithNewRate(Film film, int userRate) throws ServiceException {
        int filmId = film.getId();
        String title = film.getTitle();
        String director = film.getDirector();
        String poster = film.getPoster();
        double currentAvgRate = film.getAvgRate();

        List<UserAction> actions = findReviewsByFilmId(filmId);
        double resultAvgRate;
        if (actions.size() > 0) {
            double avgUserActionAvgRate = 0;
            for (UserAction action : actions) {
                avgUserActionAvgRate += action.getFilmRate();
            }
            avgUserActionAvgRate = avgUserActionAvgRate / actions.size();
            resultAvgRate = (currentAvgRate + avgUserActionAvgRate) / 2;
        } else {
            if (currentAvgRate != 0) {
                resultAvgRate = (currentAvgRate + userRate) / 2;
            } else {
                resultAvgRate = userRate;
            }
        }
        return new Film(filmId, title, director, poster, resultAvgRate);

    }

    private void getUserWithNewRate(DaoHelper daoHelper, int userId, int adjustNumberRate) throws DaoException {
        UserDao userDao = daoHelper.createUserDao();
        Optional<User> optionalUser = userDao.getById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String login = user.getLogin();
            String password = user.getPassword();
            String rights = user.getRights();
            boolean blocked = user.isBlocked();

            int currentUserRate = user.getRate();
            int newUserRate = currentUserRate + adjustNumberRate;
            if (newUserRate < 0) {
                userDao.save(user);
            } else {
                userDao.save(new User(userId, login, password, rights, newUserRate, blocked));
            }
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
}