package com.epam.web.rotten.potatoes.dao.impl.action;

import com.epam.web.rotten.potatoes.dao.AbstractDao;
import com.epam.web.rotten.potatoes.dao.extractor.UserActionFieldsExtractor;
import com.epam.web.rotten.potatoes.dao.mapper.UserActionRowMapper;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.sql.Connection;
import java.util.Optional;

public class UserActionDaoImpl extends AbstractDao<UserAction> implements UserActionDao {
    private static final String TABLE_NAME = "user_actions";
    private static final String INSERT_REVIEW_AND_RATE = "INSERT INTO user-actions(film-rate, review, user_id, film_id) VALUE(?,?,?,?)";

    public UserActionDaoImpl(Connection connection) {
        super(connection, new UserActionRowMapper(), new UserActionFieldsExtractor(), TABLE_NAME, INSERT_REVIEW_AND_RATE);
    }

    @Override
    public Optional<UserAction> addFilmRateAndReview(int rate, String review) throws DaoException {
        return executeForSingleResult(INSERT_REVIEW_AND_RATE, rate, review);
    }
}
