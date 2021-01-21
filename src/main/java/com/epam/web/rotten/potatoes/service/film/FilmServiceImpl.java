package com.epam.web.rotten.potatoes.service.film;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.film.FilmDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;

import java.util.List;
import java.util.Optional;

public class FilmServiceImpl implements FilmService {
    private DaoHelperFactory daoHelperFactory;

    public FilmServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Film> getPage(int page, int itemsPerPage) throws ServiceException {
        int firstItemNumber = (page - 1) * itemsPerPage + 1;
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            return filmDao.findFilmsPartList(itemsPerPage, firstItemNumber);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Film> getFilmById(Integer id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            return filmDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Film> getFilmByDirector(String director) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            return filmDao.findFilmByDirector(director);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Integer> save(Film film) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            return filmDao.save(film);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            filmDao.remove(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getCountRows(int itemsPerPage) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            int numberOfItems = filmDao.countRows();
            return (int) Math.ceil(numberOfItems / (double) itemsPerPage);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
