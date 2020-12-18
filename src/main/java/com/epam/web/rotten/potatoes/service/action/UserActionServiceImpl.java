package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.List;

public class UserActionServiceImpl implements UserActionService {
    private DaoHelperFactory daoHelperFactory;

    public UserActionServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void addReviewAndRate(UserAction userAction) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            userActionDao.save(userAction);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserAction> findAllReviewByFilmId(int filmId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();
            return userActionDao.getReviewsByFilmId(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}