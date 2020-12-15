package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.dao.extractor.UserActionFieldsExtractor;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.action.UserActionDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;

public class UserActionServiceImpl implements UserActionService {
    private DaoHelperFactory daoHelperFactory;
    private final static UserActionFieldsExtractor USER_ACTION_FIELDS_EXTRACTOR = new UserActionFieldsExtractor();

    public UserActionServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void addReviewAndRate(int rate, String review) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserActionDao userActionDao = daoHelper.createUserActionDao();



            userActionDao.addFilmRateAndReview(rate, review);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
