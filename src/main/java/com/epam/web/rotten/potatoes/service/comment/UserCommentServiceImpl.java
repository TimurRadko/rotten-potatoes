package com.epam.web.rotten.potatoes.service.comment;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.comment.UserCommentDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.List;
import java.util.Optional;

public class UserCommentServiceImpl implements UserCommentService {
    private DaoHelperFactory daoHelperFactory;

    public UserCommentServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void addComment(UserComment userComment) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserCommentDao userCommentDao = daoHelper.createUserCommentDao();
            userCommentDao.save(userComment);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserComment> findCommentsByFilmId(Integer filmId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserCommentDao userCommentDao = daoHelper.createUserCommentDao();
            return userCommentDao.getCommentsByFilmId(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserComment> findCommentsByUserId(Integer userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserCommentDao userCommentDao = daoHelper.createUserCommentDao();
            return userCommentDao.getCommentsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<UserComment> findCommentById(Integer commentId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserCommentDao userCommentDao = daoHelper.createUserCommentDao();
            return userCommentDao.getById(commentId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserCommentDao userCommentDao = daoHelper.createUserCommentDao();
            userCommentDao.remove(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
