package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.dao.impl.film.FilmDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;
import java.util.Optional;

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

            saveNewAvgRate(userAction, daoHelper);

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

    private void saveNewAvgRate(UserAction userAction, DaoHelper daoHelper) throws DaoException {
        double filmRate = userAction.getFilmRate();
        int filmId = userAction.getFilmId();

        FilmDao filmDao = daoHelper.createFilmDao();
        Optional<Film> findFilm = filmDao.getById(filmId);
        if (findFilm.isPresent()) {
            Film film = findFilm.get();
            Film newFilm = getFilmWithNewRate(filmRate, film);
            filmDao.save(newFilm);
        }
    }

    private Film getFilmWithNewRate(double filmRate, Film film) {
        double oldAvgRate = film.getAvgRate();
        double newAvgRate = (filmRate + oldAvgRate) / 2;
        double scale = Math.pow(2, 1);
        double result = Math.ceil(newAvgRate * scale) / scale;

        Integer id = film.getId();
        String title = film.getTitle();
        String director = film.getDirector();
        String poster = film.getPoster();

        return new Film(id, title, director, poster, result);
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